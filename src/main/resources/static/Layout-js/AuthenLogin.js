btnLogin = async () => {
    let userLogin = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
    }
    try {
        let response = await axios.post('/authen-api/login', userLogin);
        let userId = response.data.userId; // Đọc đúng trường userId từ phản hồi
        let userPhone = response.data.phone; // Đọc số điện thoại từ phản hồi
        console.log(userPhone)
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
                    localStorage.setItem('userId', userId);
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
                    localStorage.setItem('userId', userId); // Lưu userId vào localStorage
                    localStorage.setItem('phone', userPhone); // Lưu số điện thoại vào localStorage
                    localStorage.setItem('userId', userId); // Lưu userId vào localStorage
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