function getShippingInfo() {
    // Lấy thông tin từ các trường nhập liệu
    const name = document.getElementById('name').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const description = document.getElementById('Description').value.trim();
    const addressElement = document.getElementById('display-address');
    const address = addressElement ? addressElement.textContent.trim() : '';

    if (!name) {
        Swal.fire({
            toast: true,
            position: 'top-end',
            icon: 'warning',
            title: 'Vui lòng điền tên người nhận.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            customClass: {
                popup: 'swal2-custom-toast'
            }
        }).then(() => {
            document.getElementById('name').focus();
        });
        return;
    }

    if (!phone) {
        Swal.fire({
            toast: true,
            position: 'top-end',
            icon: 'warning',
            title: 'Vui lòng điền SDT người nhận.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            customClass: {
                popup: 'swal2-custom-toast'
            }
        }).then(() => {
            document.getElementById('phone').focus();
        });
        return;
    }

    if (!address) {
        Swal.fire({
            toast: true,
            position: 'top-end',
            icon: 'warning',
            title: 'Địa chỉ nhận hàng không được để trống.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            customClass: {
                popup: 'swal2-custom-toast'
            }
        });
        return;
    }


    // Tạo đối tượng Date cho thời gian hiện tại
    const currentTime = new Date();

    // Tính toán thời gian hiện tại cộng thêm 30 phút
    const deliveryTime = new Date(currentTime.getTime() + 30 * 60 * 1000); // 30 phút = 30 * 60 * 1000 ms

    return {
        recipientName: name,
        recipientPhone: phone,
        address : address,
        addressNote : description,
        deliveryTime : deliveryTime.toISOString()
    };
}

function placeOrder() {
    // Lấy thông tin khách hàng từ localStorage
    const customerId = localStorage.getItem('userId');
    if (!customerId) {
        console.error('Customer ID not found in localStorage.');
        return;
    }

    // Lấy tổng số tiền
    const totalAmountElement = document.getElementById('total-price');
    if (!totalAmountElement) {
        console.error('Total price element not found.');
        return;
    }
    const totalAmount = parseFloat(totalAmountElement.innerText.replace('₫', '').replace(/\./g, '').replace(',', '.'));

    // Lấy phương thức thanh toán
    const paymentMethodElement = document.querySelector('input[name="payment-method"]:checked');
    if (!paymentMethodElement) {
        Swal.fire({
            toast: true,
            position: 'top-end',
            icon: 'warning',
            title: 'Vui lòng chọn phương thức thanh toán.',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            customClass: {
                popup: 'swal2-custom-toast'
                }
        })
        return;
    }
    const paymentMethodId = parseInt(paymentMethodElement.value);

    // Lấy chi tiết đơn hàng
    const orderDetails = [];
    const orderItems = document.querySelectorAll('#selected-items-container .tch-order-card');

    if (orderItems.length === 0) {
        Swal.fire({
            position: 'center',
            icon: 'info',
            title: 'Giỏ hàng của bạn hiện không có sản phẩm nào.',
            showConfirmButton: false,
            timer: 1500
        }).then(() => {
            setTimeout(() => {
                window.location.href = '/AllProduct'; // Thay '/AllProduct' bằng đường dẫn thực tế của trang danh sách sản phẩm
            }, 3000); // Trì hoãn 3 giây trước khi chuyển hướng
        });
        return;
    }

    orderItems.forEach((item, index) => {
        const sizeTextElement = item.querySelector('.size');
        const quantityElement = item.querySelector('.quantity');
        const priceElement = item.querySelector('.tch-order-card__price');

        if (!sizeTextElement || !quantityElement || !priceElement) {
            console.error('Missing required item data.');
            return;
        }

        const productId = cart[index].productId;  // Lấy productId từ giỏ hàng
        const sizeText = sizeTextElement.textContent.trim();
        const quantity = parseInt(quantityElement.textContent.trim());
        const price = parseFloat(priceElement.innerText.replace('₫', '').replace(/\./g, '').replace(',', '.'));

        let sizeId = null;
        if (sizeText.includes('Size S')) {
            sizeId = 1;
        } else if (sizeText.includes('Size M')) {
            sizeId = 2;
        } else if (sizeText.includes('Size L')) {
            sizeId = 3;
        }

        console.log(`Product ID: ${productId}, Quantity: ${quantity}, Size ID: ${sizeId}, Price: ${price}`);

        if (productId && quantity && sizeId && !isNaN(price)) {
            orderDetails.push({ productId, quantity, sizeId, price });
        } else {
            console.error('Invalid item data:', { productId, quantity, sizeText, sizeId, price });
        }
    });

    console.log('Order Details:', orderDetails);

    if (orderDetails.length === 0) {
        console.error('Order details are empty.');
        return;
    }

    // Lấy thông tin vận chuyển
    const shippingInfo = getShippingInfo();
    if (!shippingInfo) {
        console.error('Shipping info is missing.');
        return;
    }

    // Gửi yêu cầu tạo đơn hàng
    const data = {
        customerId: customerId,
        totalAmount: totalAmount,
        paymentMethodId: paymentMethodId,
        orderDetails: orderDetails,
        shippingInfo: shippingInfo,
        orderStatusId: 3 // Gán giá trị mặc định cho orderStatusId
    };

    console.log(data);

    axios.post('http://localhost:1008/oderApi/create', data)
        .then(response => {
            Swal.fire({
                position: "center",
                icon: "success",
                title: "Đặt Hàng Thành Công Thành Công",
                showConfirmButton: false,
                timer: 1500})
            console.log('Order created successfully:', response.data)
        })
        .catch(error => {
            console.error('Error creating order:', error.response ? error.response.data : error.message);
        });
}

document.getElementById('placeOrder').addEventListener('click', placeOrder);
