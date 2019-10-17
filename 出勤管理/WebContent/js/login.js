function isVaildInfo() {
    var usenameReg = /^[0-9]{10}$/
    var pwdReg = /^[a-zA-Z0-9]{6,18}$/;

    var usename = document.getElementById("usename").value;
    var pwd = document.getElementById("pwd").value;

    if (usenameReg.test(usename) && pwdReg.test(pwd)) {
        return true;
    }
    else {
        return false;
    }
}