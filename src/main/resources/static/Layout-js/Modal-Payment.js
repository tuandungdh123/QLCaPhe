document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search-input');
    const searchResults = document.getElementById('search-results');
    const saveButton = document.getElementById('save-address');
    const displayTitle = document.getElementById('display-title');
    const displayAddress = document.getElementById('display-address');
    let selectedAddress = '';

    const addresses = [
        "223 Phạm Ngũ Lão, Phường Phạm Ngũ Lão, Quận 1, Hồ Chí Minh, Việt Nam",
        "123 Nguyễn Thị Minh Khai, Phường 5, Quận 3, Hồ Chí Minh, Việt Nam",
        "456 Lê Lợi, Phường Bến Nghé, Quận 1, Hồ Chí Minh, Việt Nam",
        "789 Trần Hưng Đạo, Phường 6, Quận 5, Hồ Chí Minh, Việt Nam",
        "101 Đinh Tiên Hoàng, Phường Đa Kao, Quận 1, Hồ Chí Minh, Việt Nam"
    ];

    searchInput.addEventListener('input', function () {
        const query = searchInput.value.toLowerCase();
        searchResults.innerHTML = '';

        if (query) {
            const filteredAddresses = addresses.filter(address => address.toLowerCase().includes(query));
            filteredAddresses.forEach(address => {
                const li = document.createElement('li');
                li.className = 'list-group-item';
                li.textContent = address;
                li.addEventListener('click', () => {
                    selectedAddress = address;
                    searchInput.value = address.split(',')[0];
                    searchResults.innerHTML = '';
                });
                searchResults.appendChild(li);
            });
        }
    });

    saveButton.addEventListener('click', function () {
        if (selectedAddress) {
            const title = selectedAddress.split(',')[0];
            displayTitle.textContent = title;
            displayAddress.textContent = selectedAddress;
            $('#exampleModal').modal('hide');
        } else {
            alert('Vui lòng chọn địa chỉ');
        }
    });
});
