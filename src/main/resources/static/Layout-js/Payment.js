let cart = [];

document.addEventListener('DOMContentLoaded', function () {
    cart = JSON.parse(localStorage.getItem('cart')) || [];
    let selectedItemsContainer = document.getElementById('selected-items-container');
    let totalPriceElement = document.getElementById('total-price');
    let totalPriceElement2 = document.getElementById('total-price2');
    const additionalFee = 18000; // Phí bổ sung 25.000 VND

    function populateCheckoutPage() {
        let totalQuantity = 0;
        let totalPrice = 0;
        let itemsHTML = '';

        cart.forEach(item => {
            const itemTotalPrice = item.price * item.quantity;
            totalQuantity += item.quantity;
            totalPrice += itemTotalPrice;

            itemsHTML += `<div class="tch-order-card d-flex align-items-center justify-content-between">
    <div class="tch-order-card__left d-flex">
        <span class="tch-order-card__icon d-flex align-items-center">
            <i class="fa fa-pen" aria-hidden="true"></i>
        </span>
        <div class="tch-order-card__content">
            <div style="display: flex; gap: 3px;">
                <h5 class="tch-order-card__title mb-0 quantity">${item.quantity}</h5>
                <h5 class="tch-order-card__title mb-0">x</h5>
                <h5 class="tch-order-card__title mb-0 name-product">${item.name}</h5>
                (<h5 class="tch-order-card__title mb-0 size">${item.sizeName}</h5>)
            </div>
            <p class="tch-order-delete-item" onclick="removeItemFromCheckout(${item.productId}, ${item.sizeId}, cart)">Xóa</p>
        </div>
    </div>
    <div class="tch-order-card__right">
        <p class="tch-order-card__price mb-0">${itemTotalPrice.toLocaleString('vi-VN', {
                style: 'currency',
                currency: 'VND'
            })}</p>
    </div>
</div>`;
        });

        const totalPriceWithFee = totalPrice + additionalFee;

        selectedItemsContainer.innerHTML = itemsHTML;
        totalPriceElement2.textContent = totalPriceWithFee.toLocaleString('vi-VN', {
            style: 'currency',
            currency: 'VND'
        });
        totalPriceElement.textContent = totalPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
    }

    populateCheckoutPage();

    window.removeItemFromCheckout = function (productId, sizeId) {
        let itemIndex = cart.findIndex(item => item.productId === productId && item.sizeId === sizeId);

        if (itemIndex > -1) {
            cart.splice(itemIndex, 1);
            localStorage.setItem('cart', JSON.stringify(cart));
            populateCheckoutPage();
        }
    }
});
// Hàm lấy dữ liệu từ API và điền vào form
async function fetchShippingInfo(customerId) {
    try {
        let response = await axios.get(`http://localhost:1008/CustomerDetail/Api/getCustomerById?customerId=${customerId}`);
        let customerData = response.data;
        console.log(customerData)

        if (customerData) {
            let userPhone = localStorage.getItem('phone');
            document.getElementById('name').value = customerData.fullName || '';
            phone.value = userPhone || ''; // Gán số điện thoại từ localStorage nếu có
        }
    } catch (error) {
        console.error('Error fetching shipping info:', error);
    }
}

// Gọi hàm fetchShippingInfo khi trang được tải
let userId = localStorage.getItem('userId');
if (userId) {
    fetchShippingInfo(userId);
}