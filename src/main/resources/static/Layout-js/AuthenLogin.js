btnLogin = async () => {
    let userLogin = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
    }
    try {
        let response = await axios.post('/authen-api/login', userLogin);
        if (response.data.success) {
            if (response.data.userType === 'admin') {
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Đăng Nhập Thành Công",
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    localStorage.setItem('account', userLogin.username)
                    window.location.href='/admin'
                });
            } else {
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Đăng Nhập Thành Công",
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    localStorage.setItem('account', userLogin.username)
                    window.location.href='/Home'
                });
            }
        } else {
            Swal.fire({
                position: "center",
                icon: "error",
                title: "Đăng nhập thất bại",
                showConfirmButton: false,
                timer: 1500
            });
        }
    } catch (error) {
        console.error('Error during login:', error);
    }
}