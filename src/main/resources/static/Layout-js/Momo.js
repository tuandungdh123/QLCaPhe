$(document).ready(function() {
    $('#placeOrder').on('click', function(event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của form
        console.log('Button clicked'); // Kiểm tra sự kiện click

        // Kiểm tra xem phương thức thanh toán được chọn có phải là MoMo không
        if ($('input[name="payment-method"]:checked').attr('id') === 'MoMo') {
            console.log('Button clicked'); // Kiểm tra sự kiện click

            var orderInfo = "Coffee Place";
            var amount = $("#total-price2").text().replace(/[₫\s]/g, '').replace(/\./g, '');

            console.log('Order Info:', orderInfo);
            console.log('Amount:', amount);
            axios.post('http://localhost:1008/api/momo', {
                orderInfo: orderInfo,
                amount: amount
            })
                .then(response => {
                    console.log('Response:', response.data); // Kiểm tra giá trị trả về từ server
                    window.location.href = response.data;
                })
                .catch(error => {
                    if (error.response) {
                        console.error('Error data:', error.response.data);
                        console.error('Error status:', error.response.status);
                        console.error('Error headers:', error.response.headers);
                    } else if (error.request) {
                        console.error('Error request:', error.request);
                    } else {
                        console.error('Error message:', error.message);
                    }
                });
        }
    });
});
