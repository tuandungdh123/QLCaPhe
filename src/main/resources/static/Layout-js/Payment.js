let cart = [];

document.addEventListener('DOMContentLoaded', function() {
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
                        <h5 class="tch-order-card__title mb-0">${item.quantity} x ${item.name}</h5>
                        <p class="tch-order-card__description mb-0">Size ${item.sizeName}</p>
                        <p class="tch-order-delete-item" onclick="removeItemFromCheckout(${item.productId}, ${item.sizeId}, cart)">Xóa</p>
                    </div>
                </div>
                <div class="tch-order-card__right">
                    <p class="tch-order-card__price mb-0">${itemTotalPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</p>
                </div>
            </div>`;
    });

    const totalPriceWithFee = totalPrice + additionalFee;

    selectedItemsContainer.innerHTML = itemsHTML;
    totalPriceElement2.textContent = totalPriceWithFee.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    totalPriceElement.textContent = totalPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}
populateCheckoutPage();

    // Hàm xóa sản phẩm khỏi trang thanh toán
    window.removeItemFromCheckout = function(productId, sizeId) {
        let itemIndex = cart.findIndex(item => item.productId === productId && item.sizeId === sizeId);

        if (itemIndex > -1) {
            cart.splice(itemIndex, 1);
            localStorage.setItem('cart', JSON.stringify(cart));
            populateCheckoutPage();
        }
    }
});