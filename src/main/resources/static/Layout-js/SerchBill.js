$(document).ready(function() {
    $('.nav-link').click(function (e) {
        e.preventDefault();
        $('.nav-link').removeClass('active');
        $(this).addClass('active');

        var tabId = $(this).attr('id');
        $('.tab-content').hide();
        $('#tabContent' + tabId.replace('tab', '')).show();
    });

    $('#searchButton').on('click', function () {
        // Lấy giá trị số điện thoại từ input
        const phone = $('#searchPhone').val();

        if (phone) {
            // Gọi API để tìm kiếm đơn hàng
            axios.get(`/oderApi/search?phone=${phone}`)
                .then(response => {
                    console.log('Dữ liệu phản hồi:', response.data);

                    let results;
                    try {
                        // Kiểm tra nếu dữ liệu trả về là chuỗi JSON
                        if (typeof response.data === 'string') {
                            results = JSON.parse(response.data);
                        } else {
                            results = response.data;
                        }

                        // Kiểm tra xem kết quả có phải là mảng và không rỗng
                        if (Array.isArray(results) && results.length > 0) {
                            // Xóa dữ liệu cũ trong tất cả các bảng
                            $('#resultsTableTab1 tbody').empty();
                            $('#resultsTableTab2 tbody').empty();
                            $('#resultsTableTab3 tbody').empty();
                            $('#resultsTableTab4 tbody').empty();

                            // Phân loại và thêm đơn hàng vào các bảng tương ứng
                            results.forEach(order => {
                                // Định dạng giá thành
                                const formattedTotalAmount = formatCurrencyVND(order.totalAmount);

                                // Tạo dòng dữ liệu
                                const row = `
                                <tr>
                                    <td>${order.orderId}</td>
                                    <td>${order.productName || 'N/A'}</td>
                                    <td>${formattedTotalAmount}</td>
                                    <td>${order.orderStatus}</td>
                                </tr>
                            `;

                                // Thêm dòng dữ liệu vào bảng tương ứng
                                switch (order.orderStatus.trim()) {
                                    case 'Đang chờ xác nhận':
                                        $('#resultsTableTab1 tbody').append(row);
                                        break;
                                    case 'Đang Giao Hàng':
                                        $('#resultsTableTab2 tbody').append(row);
                                        break;
                                    case 'Giao Hàng Thành Công':
                                        $('#resultsTableTab3 tbody').append(row);
                                        break;
                                    case 'Đơn Hàng Đã Hủy':
                                        $('#resultsTableTab4 tbody').append(row);
                                        break;
                                    default:
                                        console.warn(`Unknown status ID: ${order.orderStatus}`);
                                        break;
                                }
                            });
                        } else {
                            $('#resultsTableTab1 tbody').empty();
                            $('#resultsTableTab2 tbody').empty();
                            $('#resultsTableTab3 tbody').empty();
                            $('#resultsTableTab4 tbody').empty();
                            // Nếu không có dữ liệu hoặc mảng rỗng
                            $('#resultsTableTab1 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                            $('#resultsTableTab2 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                            $('#resultsTableTab3 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                            $('#resultsTableTab4 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                        }
                    } catch (e) {
                        $('#resultsTableTab1 tbody').empty();
                        $('#resultsTableTab2 tbody').empty();
                        $('#resultsTableTab3 tbody').empty();
                        $('#resultsTableTab4 tbody').empty();
                        // Nếu không có dữ liệu hoặc mảng rỗng
                        $('#resultsTableTab1 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                        $('#resultsTableTab2 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                        $('#resultsTableTab3 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                        $('#resultsTableTab4 tbody').append('<tr><td colspan="4" class="text-center">Không có đơn hàng nào được tìm thấy</td></tr>');
                    }

                })
                .catch(error => {
                    console.error('Có lỗi xảy ra:', error);
                    alert('Có lỗi xảy ra khi tìm kiếm đơn hàng.');
                });
        } else {
            alert('Vui lòng nhập số điện thoại để tìm kiếm.');
        }
    });

    // Xử lý sự kiện nhấp đúp vào hàng của bảng
    $('body').on('dblclick', '#resultsTableTab1 tbody tr', function() {
        const $row = $(this);
        const orderId = $row.find('td').eq(0).text();
        const productName = $row.find('td').eq(1).text();
        const totalAmount = $row.find('td').eq(2).text();
        const orderStatus = $row.find('td').eq(3).text();

        $('#exampleModalLabel').text(`Chi Tiết Đơn Hàng ${orderId}`);
        $('#exampleModal .modal-body').html(`
            <p><strong>Mã Đơn Hàng:</strong> ${orderId}</p>
            <p><strong>Tên Sản Phẩm:</strong> ${productName}</p>
            <p><strong>Tổng Giá:</strong> ${totalAmount}</p>
            <p><strong>Trạng Thái Đơn Hàng:</strong> ${orderStatus}</p>
        `);

        var myModal = new bootstrap.Modal(document.getElementById('exampleModal'));
        myModal.show();
    });
    // Khi mở modal
    $('#cancelOrderBtn').off('click').on('click', function () {
        var cancelReason = $('#cancelReason').val();

        if (cancelReason && cancelReason.trim() !== '') {
            $.ajax({
                url: '/orderApi/cancel',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    orderId: orderId,
                    cancelReason: cancelReason.trim()
                }),
                success: function (response) {
                    alert('Đơn hàng đã được hủy thành công.');
                    $('#exampleModal').modal('hide');
                },
                error: function (xhr, status, error) {
                    alert('Lỗi khi hủy đơn hàng: ' + error);
                }
            });
        } else {
            alert('Vui lòng nhập lý do hủy.');
        }
    });


    // Hàm định dạng giá thành tiền Việt
    function formatCurrencyVND(amount) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
            minimumFractionDigits: 0
        }).format(amount);
    }
});
