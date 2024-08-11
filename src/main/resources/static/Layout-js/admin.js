document.getElementById('Statistical').addEventListener('change', function() {
    var selectedValue = document.getElementById('Statistical').value;

    // var Day = document.getElementById('statisticalDay');
    var Month = document.getElementById('statisticalMonth');
    var Year = document.getElementById('statisticalYear');
    // var buttonDay = document.getElementById('buttonDay');
    var buttonMonth = document.getElementById('buttonMonth');
    var buttonYear = document.getElementById('buttonYear');
    // var chartDay = document.getElementById('chartDay')
    var chartMonth = document.getElementById('chartMonth')
    var chartYear = document.getElementById('chartYear')
    // var BarChartDay = document.getElementById('BarChartDay')
    var BarChartMonth = document.getElementById('BarChartMonth')
    var BarChartYear = document.getElementById('BarChartYear')

    // Day.style.display = 'none';
    Month.style.display = 'none';
    Year.style.display = 'none';
    // buttonDay.style.display = 'none';
    buttonMonth.style.display = 'none';
    buttonYear.style.display = 'none';
    // chartDay.style.display = 'none';
    chartMonth.style.display = 'none';
    chartYear.style.display = 'none';
    // BarChartDay.style.display = 'none';
    BarChartMonth.style.display = 'none';
    BarChartYear.style.display = 'none';



    if (selectedValue === 'Day') {
        Day.style.display = 'block';
        buttonDay.style.display = 'block';
        chartDay.style.display = 'block'
        BarChartDay.style.display = 'block'
    } else if (selectedValue === 'Month') {
        Month.style.display = 'block';
        buttonMonth.style.display = 'block';
        chartMonth.style.display = 'block'
        BarChartMonth.style.display = 'block'
    } else if (selectedValue === 'Year') {
        Year.style.display = 'block';
        buttonYear.style.display = 'block';
        chartYear.style.display = 'block'
        BarChartYear.style.display = 'block'
    }
});
