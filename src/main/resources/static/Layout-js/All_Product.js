var listAllProduct;

async function loadDataProduct() {
    try {
        let response = await axios.get(`/productApi/getAllProduct`);
        listAllProduct = response.data.data;
    } catch (error) {
    }
}

$(document).ready(async function () {
    await loadDataProduct();
    createTableProduct(listAllProduct); // Gọi hàm createTableTour sau khi loadDataTour đã hoàn tất
});

function createTableProduct(addToTable) {
    let sizePrices = {};  // Đảm bảo khai báo biến trước khi sử dụng
    let populateProductString = '';


    const maxProducts = 8;
    const productsToDisplay = addToTable.slice(0, maxProducts);

    productsToDisplay.forEach(function (e) {


        const firstSize = e.productSizes[0]; // Lấy kích thước đầu tiên
        const price = firstSize ? firstSize.price : 'Chưa có giá';


        populateProductString +=
            `
        <div class="col-sm-3 product-card" product-id="350472" style="margin-left: 30px;">
        <div class="tags">   
        <div class="discount-tag">17%</div>  </div>
        <div class="img-wrap">
            <img src="/images/ImagesProduct/${e.productImage}" alt="ĐungauBui">
        </div>
        <div class="product-card-content">
            <div class="product-title" style="height: 72px"> ${e.nameProduct} </div>
            <div class="product-price">
                <div class="product-origin-price">
                    ${price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}</div>
                <div class="product-sale-price">
                    <em>Chưa có giá giãm</em> </div>
            </div>
            <div class="button-type-one btn-order-product" onclick="window.location.href='/AllProduct?productId=${e.productId}'">Đặt Hàng</div>
        </div>
            <hr>
    </div>`
    });
    $("#BodyListAllProduct").html(populateProductString);
}
