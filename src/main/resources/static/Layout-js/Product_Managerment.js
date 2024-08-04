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

    addToTable.forEach(function (e, index) {
        populateProductString +=
            `<tr>` +
            `<td>${index + 1}</td>`+
            `<td>${e.nameProduct}</td>` +
            `<td><img src="${e.productImage}" alt="${e.nameProduct}" style="width: 50px; height: 50px;"></td>` +
            `<td>${e.productTypeE.productTypeName}</td>` +
            `<td>Size S: ${ e.productSizes.productTypeId = 1 / e.productSizes.price}<br>` +
            `Size M: ${sizePrices['M'] ? sizePrices['M'] : e.productSizes.price}<br>` +
            `Size L: ${sizePrices['L'] ? sizePrices['L'] : e.productSizes.price}</td>` +
            `<td>` +
            `<button type="button" class="btn btn-outline-success" onclick="getProductToForm(${e.productId})">Edit</button>` +
            `</td>` +
            `</tr>`;
    });
    $("#BodyListAllProduct").html(populateProductString);
}

// Save
async function SaveProductInForm() {
    // Lấy dữ liệu từ các trường nhập liệu
    const nameProduct = $("#nameTour").val();
    const productImage = $("#imageInput").val().split('\\').pop();  // Lấy tên tệp tin từ đường dẫn
    const productTypeId = parseInt($("#type_Id").val());
    const description = $("#Note").val();
    const productSizes = [
        {
            sizeId: 1,
            price: parseFloat($("#Price").val())
        },
        {
            sizeId: 2,
            price: parseFloat($("#PriceEm_Children").val())
        },
        {
            sizeId: 3,
            price: parseFloat($("#Price_SizeL").val())
        }
    ];

//     update


// Tạo đối tượng TourData
    const TourData = {
        nameProduct: nameProduct,
        productImage: productImage,
        productTypeId: productTypeId,
        description: description,
        productSizes: productSizes
    };

    console.log(TourData);


    Swal.fire({
        title: 'Lưu Tour Này Chứ?',
        text: 'Bạn có chắn chắn là lưu Tour này?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Chắc, lưu Tour này!',
        cancelButtonText: 'Cancel'
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                let response = await axios.post('/productApi/getSaveProduct', TourData);
                await upLoadFile();
                await loadDataProduct();
                createTableProduct(listAllProduct);
                // clearForm(); // Xóa form sau khi lưu thành công
                Swal.fire(
                    'Lưu Thành Công!',
                    'Đã lưu tour thành công.',
                    'success'
                );
                console.log(response.data)
            } catch (error) {
                console.error('Error saving product:', error.response ? error.response.data : error.message)
                Swal.fire(
                    'Error!',
                    'An error occurred while saving the Product.',
                    'error'
                );
            }
        }
    });
}

async function upLoadFile() {
    var formData = new FormData();
    var fileInput = $('#imageInput')[0];

    // Kiểm tra xem đã chọn file chưa
    if (fileInput.files.length === 0) {
        return;
    }

    formData.append('file', fileInput.files[0]);

    let response = await axios.post('/upload-api/upload', formData, {})
        .then(function (response) {
        })
        .catch(function (error) {
        });
}
    async function UpdateProductInForm() {
        // Lấy dữ liệu từ các trường nhập liệu
        const productId = $("#ProductId").val();
        const nameProduct = $("#nameTour").val();
        const productImage = $("#imageInput").val().split('\\').pop();  // Lấy tên tệp tin từ đường dẫn
        const productTypeId = parseInt($("#type_Id").val());
        const description = $("#Note").val();
        const productSizes = [
            {
                sizeId: 1,
                price: parseFloat($("#Price").val())
            },
            {
                sizeId: 2,
                price: parseFloat($("#PriceEm_Children").val())
            },
            {
                sizeId: 3,
                price: parseFloat($("#Price_SizeL").val())
            }
        ];
        const ProductData = {
            productId : productId,
            nameProduct: nameProduct,
            productImage: productImage,
            productTypeId: productTypeId,
            description: description,
            productSizes: productSizes
        };
        console.log(TourData);


        Swal.fire({
            title: 'Xác nhận chỉnh sữa?',
            text: 'Bạn có chắn chắn Xác nhận chỉnh sữa này?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: 'Chắc, lưu Tour này!',
            cancelButtonText: 'Cancel'
        }).then(async (result) => {
            if (result.isConfirmed) {
                try {
                    let response = await axios.post('/productApi/getSaveProduct', ProductData);
                    await upLoadFile();
                    await loadDataProduct();
                    createTableProduct(listAllProduct);
                    // clearForm(); // Xóa form sau khi lưu thành công
                    Swal.fire(
                        'Cập nhật thành công!',
                        'Cập nhật thành công thành công.',
                        'success'
                    );
                    console.log(response.data)
                } catch (error) {
                    console.error('Error saving product:', error.response ? error.response.data : error.message)
                    Swal.fire(
                        'Error!',
                        'An error occurred while saving the Product.',
                        'error'
                    );
                }
            }
        });
}
