btnRegister_click = async () =>{
    if(!validateFormUserName()){
        return;
    }
    if(!validateFormPassWord()){
        return;
    }
    if(!validateFormRepeatPassWord()){
        return;
    }
    if(!validateSamePassWord()){
        return;
    }
    if(!validateFormPhone()){
        return;
    }
    let customerData = {
        username: $("#username").val(),
        password : $("#password").val(),
        phone : $("#phone").val(),

    }
    let response = await axios.post('register-api/register', customerData);
    if(response.status === 200 && response.data.status){
        Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Đăng ký Thành Công",
            showConfirmButton: false,
            timer: 1500
        }).then(() => {
            window.location.href='/login'
        });
    } else {
        Swal.fire({
            position: "top-end",
            icon: "error",
            title: "Đăng ký thất bại",
            showConfirmButton: false,
            timer: 1500
        });
    }
}
clearForm = () =>{
    fillDetailForm({
        accountName: '',
        password: '',
        phone: '',
    });
}
validateFormUserName = () =>{
    if(!$("#username").val()){
        Swal.fire({
            icon: "error",
            title: "Vui lòng nhập tài khoản",
            showConfirmButton: false,
            timer: 1500
        });
        return false;
    }
    return true;
}
validateFormPassWord = () =>{
    if (!$("#password").val()){
        Swal.fire({
            icon: "error",
            title: "Vui lòng nhập mật khẩu",
            showConfirmButton: false,
            timer: 1500
        });
        return false;
    }
    let regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    if(!regex.test($("#password").val())){
        Swal.fire({
            icon: "error",
            title: "Hãy nhập mật khẩu gồm in hoa, in thường và số, tối thiểu là 8 chữ số",
            showConfirmButton: false,
            timer: 2000
        });
        return false;
    }
    return true;
}

validateFormRepeatPassWord = () =>{
    if (!$("#repeatPassword").val()){
        Swal.fire({
            icon: "error",
            title: "Vui lòng nhập lại mật khẩu",
            showConfirmButton: false,
            timer: 1500
        });
        return false;
    }

    return true;
}
validateSamePassWord = () =>{
    if ($("#password").val() !== $("#repeatPassword").val()){
        Swal.fire({
            icon: "error",
            title: "Vui lòng nhập đúng mật khẩu",
            showConfirmButton: false,
            timer: 1500
        });
        return false;
    }
    return true;
}
validateFormPhone = () =>{
    if (!$("#phone").val()){
        Swal.fire({
            icon: "error",
            title: "Vui lòng nhập số điện thoại",
            showConfirmButton: false,
            timer: 1500
        });
        return false;
    }
    let regexPhone = /^[0-9]{10}$/;
    if(!regexPhone.test($("#phone").val())){
        Swal.fire({
            icon: "error",
            title: "Vui lòng nhập đủ 10 số",
            showConfirmButton: false,
            timer: 1500
        });
        return false;
    }
    return true;
}

