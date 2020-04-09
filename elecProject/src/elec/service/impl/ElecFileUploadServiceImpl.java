package elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import elec.dao.IElecFileUploadDao;
import elec.domain.ElecFileUpload;
import elec.service.IElecFileUploadService;
import elec.utils.DateUtils;
import elec.utils.FileUploadUtils;
import elec.utils.LuceneUtils;


@Service(IElecFileUploadService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecFileUploadServiceImpl implements IElecFileUploadService {

	@Resource(name=IElecFileUploadDao.SERVICE_NAME)
	private IElecFileUploadDao elecFileUploadDao;
	
	/**指定条件查询索引库，获取索引库查询的结果，并获取到查询结果中的主键ID，使用主键ID查询数据库，返回数据结果，并设置文字高亮*/
	public List<ElecFileUpload> findFileUploadList(ElecFileUpload elecFileUpload) {
		/***************************使用lucene全文检索**********************************************************/
		//封装结果集
		List<ElecFileUpload> fileList = new ArrayList<ElecFileUpload>();
		//搜索条件
		String queryString = elecFileUpload.getQueryString();
		LuceneUtils luceneUtils = new LuceneUtils();
		//查询索引库，传递文件名和文件描述的搜索条件、所属单位、图纸类别
		List<ElecFileUpload> list = luceneUtils.searchFileUploadByCondition(queryString,elecFileUpload.getProjId(),elecFileUpload.getBelongTo());
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				ElecFileUpload fileUpload = list.get(i);
				//获取主键ID，主键ID检索性能不会出现问题
				Integer seqId = fileUpload.getSeqId();
				String condition = " and a.seqId=?";
				List<Object> paramsList = new ArrayList<Object>();
				paramsList.add(seqId);
				Object [] params = paramsList.toArray();
				List<ElecFileUpload> uploadList = elecFileUploadDao.findElecFileUploadByCondition(condition, params);
				if(uploadList!=null && uploadList.size()>0){
					ElecFileUpload upload = uploadList.get(0);
					//设置文字高亮的值，高亮的字段包括文件名和文件描述
					upload.setFileName(fileUpload.getFileName());
					upload.setComment(fileUpload.getComment());
					fileList.add(upload);
				}
			}
		}
		return fileList;
	}

	/**保存，将需要查询和高亮的字段存放到索引库中，同时将数据组织PO对象，保存到数据库中*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void save(ElecFileUpload elecFileUpload) {
		//获取上传的文件
		File [] files = elecFileUpload.getUploads();
		//获取上传的文件名
		String [] fileNames = elecFileUpload.getUploadsFileName();
		//获取上传的文件需要进行搜素的描述
		String [] comments = elecFileUpload.getComments();
		if(files!=null && files.length>0){
			for(int i=0;i<files.length;i++){
				File file = files[i];
				//执行保存
				ElecFileUpload fileUpload = new ElecFileUpload();
				//所属单位
				fileUpload.setProjId(elecFileUpload.getProjId());
				//图纸类别
				fileUpload.setBelongTo(elecFileUpload.getBelongTo());
				//文件名
				fileUpload.setFileName(fileNames[i]);
				//文件路径
				fileUpload.setFileUrl(FileUploadUtils.fileUploadReturnPath(file,fileNames[i]));
				//上传时间
				fileUpload.setProgressTime(DateUtils.dateToString(new Date()));//上传时间
				//文件描述
				fileUpload.setComment(comments[i]);
				elecFileUploadDao.save(fileUpload);
				//同时向索引库中添加数据
				LuceneUtils luceneUtils = new LuceneUtils();
				luceneUtils.saveFileUpload(fileUpload);
			}
		}
	}

	
	public ElecFileUpload findFileUploadByID(ElecFileUpload elecFileUpload) {
		//获取ID
		Integer seqId = elecFileUpload.getSeqId();
		ElecFileUpload fileUpload = elecFileUploadDao.findObjectByID(seqId);
		return fileUpload;
	}
	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteFileUploadByID(ElecFileUpload elecFileUpload) {
		//获取ID
		Integer seqId = elecFileUpload.getSeqId();
		//使用ID，查询资料图纸，从而获取路径path
		ElecFileUpload fileUpload = elecFileUploadDao.findObjectByID(seqId);
		//使用路径path删除附件
		String path = fileUpload.getFileUrl();
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		/**删除索引库*/
		LuceneUtils luceneUtils = new LuceneUtils();
		luceneUtils.deleteFileUploadByID(seqId);
		/**删除数据库*/
		elecFileUploadDao.deleteObjectByIds(seqId);
		
	}
	
	/**使用查询条件，获取查询的列表*/
	public List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload elecFileUpload) {
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		//所属单位
		if(StringUtils.isNotBlank(elecFileUpload.getProjId())){
			condition += " and a.projId = ?";
			paramsList.add(elecFileUpload.getProjId());
		}
		//图纸类别
		if(StringUtils.isNotBlank(elecFileUpload.getBelongTo())){
			condition += " and a.belongTo = ?";
			paramsList.add(elecFileUpload.getBelongTo());
		}
		Object [] params = paramsList.toArray();
		List<ElecFileUpload> list = elecFileUploadDao.findElecFileUploadByCondition(condition,params);
		return list;
	}
}
