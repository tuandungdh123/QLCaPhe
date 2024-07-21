document.addEventListener('DOMContentLoaded', function () {
    var orderPopup = document.getElementById('orderPopup');
    var closeBtn = document.getElementById('closeBtn');
    var orderButtons = document.querySelectorAll('.btn-order-product');

    // Show the popup
    function showPopup() {
        orderPopup.style.display = 'block';
    }

    // Hide the popup
    function hidePopup() {
        orderPopup.style.display = 'none';
    }

    // Event listener for close button
    closeBtn.addEventListener('click', hidePopup);

    // Event listeners for order buttons
    orderButtons.forEach(function (button) {
        button.addEventListener('click', showPopup);
    });
});