$(document).ready(() => {
    $("#helloAccount").text("Xin Chào," + localStorage.getItem("account")+ "!")
    btnYear_Click()
})
window.addEventListener('pageshow', function (event) {
    if (event.persisted) {
        if (!localStorage.getItem('account')) {
            window.location.href = '/login'
        }
    }
});


btnYear_Click = async () => {
    let param = {
        year : $("#statisticalYear").val()
    }
    let {data:response} = await axios.get("/statistical-api/findByYear", {params : param})

    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: Object.keys(response.data),
            datasets: [{
                label: 'Doanh Thu',
                data: Object.values(response.data),
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}


