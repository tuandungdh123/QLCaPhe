var listAllProduct;

async function loadDataProduct() {
    try {
        let response = await axios.get(`/productApi/getAllProduct`);
        listAllProduct = response.data.data;
    } catch (error) {
        console.error('Error fetching all products', error);
    }
}

$(document).ready(async function () {
    await loadDataProduct();
    createTableProduct(listAllProduct);
});

function createTableProduct(addToTable) {
    let populateProductString = '';

    addToTable.forEach(function (e) {
        const firstSize = e.productSizes[0];
        const price = firstSize ? firstSize.price : 'Chưa có giá';

        populateProductString += `
            <div class="col-sm-4 product-card" product-id="${e.productId}">
                <div class="tags">
                    <div class="discount-tag">17%</div>
                </div>
                <div class="img-wrap">
                    <img src="/images/ImagesProduct/${e.productImage}" alt="">
                </div>
                <div class="product-card-content">
                    <div class="product-title" style="font-size: 14px">${e.nameProduct}</div>
                    <div class="product-price">
                        <div class="product-origin-price">${price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</div>
                        <div class="product-sale-price">
                            <em>Hiện chưa có giá giảm</em>
                        </div>
                    </div>
                    <div class="button-type-one btn-order-product" product-id="${e.productId}">Đặt Hàng</div>
                </div>
                <hr>
            </div>`;
    });

    $("#BodyListAllProduct_List").html(populateProductString);

    $(".btn-order-product").on("click", function () {
        const productId = $(this).attr("product-id");
        showProductModal(productId);
    });
}

async function showProductModal(productId) {
    try {
        const productResponse = await axios.get('/productApi/getProductById', {
            params: { productId: productId }
        });
        const product = productResponse.data.data;

        const modalImage = document.querySelector('.product-image');
        const modalTitle = document.querySelector('.product-title-modal');
        const modalNewPrice = document.querySelector('.new-price');
        const modalDescription = document.querySelector('.product-description');
        const modalPlusPrice = document.querySelector('.Add-Cart-Price');
        const sizeSelect = document.getElementById('size-select-modal');

        if (modalImage) {
            modalImage.src = `/images/ImagesProduct/${product.productImage}`;
        }
        if (modalTitle) {
            modalTitle.textContent = product.nameProduct;
        }
        if (modalNewPrice) {
            modalNewPrice.textContent = '';
        }
        if (modalPlusPrice) {
            modalPlusPrice.textContent = '';
        }
        if (modalDescription) {
            modalDescription.textContent = product.description;
        }

        if (sizeSelect) {
            sizeSelect.innerHTML = product.productSizes.map(size =>
                `<option value="${size.sizeE.sizeId}">
                    ${size.sizeE.sizeName}
                </option>`
            ).join('');

            const updatePrice = async () => {
                const selectedSizeId = sizeSelect.value;
                try {
                    const priceResponse = await axios.get('/productApi/getPriceByProductIdAndSizeId', {
                        params: { productId: productId, sizeId: selectedSizeId }
                    });
                    const priceData = priceResponse.data.data;

                    if (priceData) {
                        const price = priceData.price;
                        modalPlusPrice.textContent = price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                        modalNewPrice.textContent = price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    } else {
                        modalNewPrice.textContent = 'Giá không có';
                    }
                } catch (error) {
                    console.error('Error fetching size price', error);
                }
            };

            await updatePrice();

            sizeSelect.addEventListener('change', updatePrice);
        }

        const addToCartBtn = document.querySelector('.add-to-cart-btn');
        if (addToCartBtn) {
            addToCartBtn.onclick = () => {
                addToCart(product);
            };
        }

        $('#exampleModal').modal('show');
    } catch (error) {
        console.error('Error loading product data', error);
    }
}

document.getElementById('closeBtn').addEventListener('click', function() {
    $('#exampleModal').modal('hide');
});

let cart = JSON.parse(localStorage.getItem('cart')) || [];

function addToCart(product) {
    const sizeSelect = document.getElementById('size-select-modal');
    const selectedSizeId = sizeSelect.value;
    const selectedSize = product.productSizes.find(size => size.sizeE.sizeId == selectedSizeId);
    const quantity = 1;

    let existingItem = cart.find(item => item.productId === product.productId && item.sizeId === selectedSize.sizeE.sizeId);

    if (existingItem) {
        existingItem.quantity += quantity;
    } else {
        cart.push({
            productId: product.productId,
            sizeId: selectedSize.sizeE.sizeId,
            name: product.nameProduct,
            sizeName: selectedSize.sizeE.sizeName,
            price: selectedSize.price,
            quantity: quantity
        });
    }

    localStorage.setItem('cart', JSON.stringify(cart));

    updateCartUI();

    $('#exampleModal').modal('hide');
}

function updateCartUI() {
    let cartHTML = '';
    let totalQuantity = 0;
    let totalPrice = 0;

    cart.forEach(item => {
        const itemTotalPrice = item.price * item.quantity;
        totalQuantity += item.quantity;
        totalPrice += itemTotalPrice;

        cartHTML +=
            `<div class="cart-ss1-item" id="item-${item.productId}-${item.sizeId}">
                <div class="cart-ss1-left">
                    <div class="name">${item.name} (${item.sizeName})</div>
                    <div class="customize">100% đường, 100% đá</div>
                    <div class="total">${item.price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })} x ${item.quantity} = ${itemTotalPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</div>
                </div>
                <div class="cart-ss1-right">
                    <div class="change-quantity decrease" onclick="updateQuantity(${item.productId}, ${item.sizeId}, -1)">-</div>
                    <div class="amount">${item.quantity}</div>
                    <div class="change-quantity increase" onclick="updateQuantity(${item.productId}, ${item.sizeId}, 1)">+</div>
                </div>
            </div>`;
    });

    $('#SanPham').html(cartHTML);

    $('.cart-ss2-two').text(totalQuantity);
    $('.cart-ss2-four').text(totalPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' }));
}

function updateQuantity(productId, sizeId, change) {
    let item = cart.find(item => item.productId === productId && item.sizeId === sizeId);

    if (item) {
        item.quantity += change;

        if (item.quantity <= 0) {
            cart = cart.filter(i => i !== item);
        }

        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartUI();
    }
}

function clearCart() {
    localStorage.removeItem('cart');
    cart = [];
    updateCartUI();
}

document.getElementById('clear-cart').addEventListener('click', function() {
    // if (confirm('Bạn có chắc chắn muốn xóa tất cả sản phẩm khỏi giỏ hàng?')) {
        clearCart();

});
