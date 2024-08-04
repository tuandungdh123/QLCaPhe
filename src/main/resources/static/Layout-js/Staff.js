var listAccount = []
$(document).ready(async function () {
    await loadDataAccount();
    $("#helloAccount").text("Xin Chào," + localStorage.getItem("account") + "!")
});

function createTableAccount() {
    if ($.fn.dataTable.isDataTable('#fillToForm')) {
        $('#fillToForm').DataTable().destroy();
    }
    let header = ` <table class="table table-bordered" id="fillToForm">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>Staff_Id</th>
                    <th>FullName</th>
                    <th>Account</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Birthday</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody id="bodyTableListAccount">`
    let body = ``
    listAccount.forEach((e, index) => {
        body += `<tr>` +
            `<td>${index + 1}</td>` +
            `<td>${e.staff_Id}</td>` +
            `<td>${e.name}</td>` +
            `<td>${e.account}</td>` +
            `<td>${e.phone}</td>` +
            `<td>${e.email}</td>` +
            `<td>${e.address}</td>` +
            `<td>${e.birthday}</td>` +
            `<td>${e.status}</td>` +
            `</tr>`;
    })
    let footer = `</tbody></table>`
    let result = header + body + footer
    $('#fillToForm').html(result);
    let table = new DataTable('#fillToForm', {
        searching: false,
        info: false,
        paging: true,
        ordering: false,
        lengthMenu: [3]
    });
    $('.dt-length').hide();
    table.on('dblclick', 'tbody tr', function (event) {
        let data = $(this).find('td').map(function() {
            return $(this).text();
        }).get();
        fillDetailForm({
            staff_Id: data[1],
            name: data[2],
            account: data[3],
            phone: data[4],
            email: data[5],
            address: data[6],
            birthday: data[7],
            status: data[8]
        })
    });
    fillDetailForm(listAccount[0])
}

async function loadDataAccount() {
    let response = await axios.get(`/staff-api/getAllAccount`);
    listAccount = response.data.data
    createTableAccount()
}

async function deleteAccount() {
    let param = {
        Staff_Id: $('#StaffId').val()
    }
    let response = await axios.delete(`/staff-api/DeleteId`, {params: param});
    let result = response.data;
    if (result.success) {
        alert('Xóa Account thành công');
        loadDataAccount();
        clearForm();
    } else {
        alert('Xóa Account thất bại');
    }
}

async function updateAccountInForm() {
    let staffData = {
        staff_Id: $("#StaffId").val(),
        name: $("#fullName").val(),
        account: $("#account").val(),
        password: '123',
        phone: $("#phone").val(),
        email: $("#email").val(),
        address: $("#address").val(),
        birthday: $("#birthday").val(),
        status: $("#status").val(),
        isStaff: '1',
    }
    let response = await axios.post(`staff-api/addAccount`, staffData)
    let result = response.data;
    if (result.success) {
        alert('Update Account thành công');
        loadDataAccount();
        clearForm();
    } else {
        alert('Update Account thất bại');
    }
}

async function saveAccountInForm() {
    let staffData = {
        name: $("#fullName").val(),
        account: $("#account").val(),
        password: '123',
        phone: $("#phone").val(),
        email: $("#email").val(),
        address: $("#address").val(),
        birthday: $("#birthday").val(),
        status: $("#status").val(),
        isStaff: '1',
    }
    let response = await axios.post(`/staff-api/addAccount`, staffData)
    let result = response.data;

    if (result.success) {
        alert('Save thông tin thành công');
        await loadDataAccount()
        clearForm();
    } else {
        alert('Save thông tin thất bại');
    }
}

function clearForm() {
    fillDetailForm({
        staff_Id: '',
        name: '',
        account: '',
        phone: '',
        email: '',
        address: '',
        birthday: '',
        status: 'Đang làm'
    });
}

function fillDetailForm(staffDetails) {
    $("#StaffId").val(staffDetails.staff_Id);
    $("#fullName").val(staffDetails.name);
    $("#account").val(staffDetails.account);
    $("#phone").val(staffDetails.phone);
    $("#email").val(staffDetails.email);
    $("#address").val(staffDetails.address)
    $("#birthday").val(staffDetails.birthday)
    $("#status").val(staffDetails.status)
}