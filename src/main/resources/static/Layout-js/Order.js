function placeOrder() {
    const selectedPaymentMethod = document.querySelector('input[name="payment-method"]:checked');
    const customerId = 1
    const totalAmount = parseFloat(document.getElementById('total-price').innerText.replace('đ', '').replace(',', '.'));

    const orderDetails = [];
    document.querySelectorAll('.order-detail-item').forEach(item => {
        const productId = item.getAttribute('data-product-id');
        const quantity = item.querySelector('.quantity').value;
        const sizeId = item.querySelector('.size').value;
        const price = parseFloat(item.querySelector('.price').innerText.replace('đ', '').replace(',', '.'));
        orderDetails.push({ productId, quantity, sizeId, price });
    });

    if (!selectedPaymentMethod) {
        alert('Please select a payment method.');
        return;
    }

    if (orderDetails.length === 0) {
        console.error('Order details are empty.');
        return;
    }

    const data = {
        customerId: customerId,
        totalAmount: totalAmount,
        paymentMethod: paymentMethod,
        orderDetails: orderDetails
    };


    axios.post('http://localhost:1008/oderApi/create', data)
        .then(response => {
            console.log('Order created successfully', response.data);
        })
        .catch(error => {
            console.error('Error creating order:', error.response ? error.response.data : error.message);
        });
}