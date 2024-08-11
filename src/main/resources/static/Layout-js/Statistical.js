$(document).ready(() => {
    $("#helloAccount").text("Xin Chào," + localStorage.getItem("account")+ "!")
})
window.addEventListener('pageshow', function (event) {
    if (event.persisted) {
        if (!localStorage.getItem('account')) {
            window.location.href = '/login'
        }
    }
});

let myChart;
btnYear_Click = async () => {
    let param = {
        year : $("#statisticalYear").val()
    }
    let {data:response} = await axios.get("/statistical-api/findByYear", {params : param})

    const ctx = document.getElementById('BarChartYear');
    if (myChart) {
        myChart.destroy();
    }

    myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: Object.keys(response.data),
            datasets: [{
                label: 'Doanh Thu Năm',
                data: Object.values(response.data),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
                ],
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

btnMonth_Click = async () => {
    let param = {
        month : $("#statisticalMonth").val()
    }
    let {data:response} = await axios.get("/statistical-api/findByMonth", {params : param})
    console.log(response)
    const ctx = document.getElementById('BarChartMonth');

    if (myChart) {
        myChart.destroy();
    }

    myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: Object.keys(response.data),
            datasets: [{
                label: 'Doanh Thu Năm',
                data: Object.values(response.data),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
                ],
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


