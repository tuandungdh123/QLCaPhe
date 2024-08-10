// Hàm để lấy thông tin khi tải trang
window.onload = function () {
    // Khai báo và khởi tạo các biến cho phần tử input
    let fullNameInput = document.getElementById('FullName');
    let dateOfBirthInput = document.getElementById('date-group1-2');
    let emailInput = document.getElementById('EmailUser');
    let phoneInput = document.getElementById('phoneInput');
    let addressInput = document.getElementById('address');
    let genderSelect = document.getElementById('exampleFormControlSelect1');
    let fileInput = document.getElementById('fileInput');
    let avatarShadow = document.querySelector('.avatar-shadow');

    // Lấy dữ liệu từ localStorage
    let userPhone = localStorage.getItem('phone');
    let userId = localStorage.getItem('userId'); // Nếu bạn cũng cần lấy userId từ localStorage

    if (fullNameInput && dateOfBirthInput && emailInput && phoneInput && addressInput && genderSelect && fileInput) {
        // Gán giá trị từ localStorage hoặc để trống nếu không có giá trị
        phoneInput.value = userPhone ; // Gán số điện thoại từ localStorage nếu có
        fullNameInput.value = ''; // Điền giá trị nếu có sẵn
        dateOfBirthInput.value = ''; // Điền giá trị nếu có sẵn
        emailInput.value = ''; // Điền giá trị nếu có sẵn
        addressInput.value = ''; // Điền giá trị nếu có sẵn
        genderSelect.value = '1'; // Ví dụ chọn giá trị mặc định (Nam)
    } else {
        console.error('Một hoặc nhiều phần tử không được tìm thấy.');
    }

    async function btnSave() {

        let selectedGenderText = genderSelect.options[genderSelect.selectedIndex].text;

        let DataAccountUser = {
            detailId: parseInt(localStorage.getItem('userId')), // Đảm bảo là số
            customer: {
                customerId: parseInt(localStorage.getItem('userId')) // Đảm bảo là số
            },
            fullName: fullNameInput.value,
            dateOfBirth: dateOfBirthInput.value,
            email: emailInput.value,
            defaultAddress: addressInput.value,
            gender: selectedGenderText, // Lấy văn bản của option đã chọn
            profileImage: fileInput.value.split('\\').pop() // Lấy tên file
        };

        console.log(DataAccountUser);
        try {
            let response = await axios.post('/CustomerDetail/Api/details', DataAccountUser);
            await upLoadFile();
            if (response.data.success) {
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Thông tin đã được lưu thành công",
                    showConfirmButton: false,
                    timer: 1500
                });
            } else {
                Swal.fire({
                    position: "center",
                    icon: "success",
                    title: "Thông tin đã được lưu thành công",
                    showConfirmButton: false,
                    timer: 1500
                });
            }
        } catch (error) {
            console.error('Error saving customer details:', error);
        }
    }

    // Kiểm tra xem phần tử 'btnSave' có tồn tại không
    let saveButton = document.getElementById('btnSave');
    if (saveButton) {
        saveButton.addEventListener('click', btnSave);
    } else {
        console.error('Phần tử nút lưu với id "btnSave" không được tìm thấy.');
    }

    async function upLoadFile() {
        var formData = new FormData();
        var fileInputElement = document.getElementById('fileInput');

        // Kiểm tra xem đã chọn file chưa
        if (fileInput.files.length === 0) {
            return;
        }

        formData.append('file', fileInputElement.files[0]);

        let response = await axios.post('/upload-api/uploadFileUser', formData, {})
            .then(function (response) {
            })
            .catch(function (error) {
            });
    }

    // Hàm để lấy dữ liệu từ API
    async function fetchCustomerDetails(customerId) {
        try {
            let response = await axios.get(`/CustomerDetail/Api/getCustomerById?customerId=${customerId}`);
            let customerData = response.data;

            // Kiểm tra và điền dữ liệu vào các trường của form
            if (customerData) {
                fullNameInput.value = customerData.fullName || '';
                dateOfBirthInput.value = customerData.dateOfBirth || '';
                emailInput.value = customerData.email || '';
                addressInput.value = customerData.defaultAddress || '';

                // Chọn giá trị của select box dựa trên text
                for (let i = 0; i < genderSelect.options.length; i++) {
                    if (genderSelect.options[i].text === customerData.gender) {
                        genderSelect.selectedIndex = i;
                        break;
                    }
                }

                if (customerData.profileImage) {
                    avatarShadow.style.backgroundImage = `url('/images/ImagesUser/${customerData.profileImage}')`;
                }
            }
        } catch (error) {
            console.error('Error fetching customer details:', error);
        }
    }

    // Gọi hàm fetchCustomerDetails khi trang được tải
    if (userId) {
        fetchCustomerDetails(userId);
    }
}
