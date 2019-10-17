package dao;

public class Auth {
	public static boolean Shenfen( String session_type) {
			try {
			    if(session_type.equals("teacher"))
				        return true;
				else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
	}
}
