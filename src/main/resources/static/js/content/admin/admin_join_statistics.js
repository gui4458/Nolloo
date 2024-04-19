//Doughnut chart
new Chart(document.querySelector('#doughnut-chart'), {
  type: 'doughnut',
  data: {
      labels: ["Africa", "Asia", "Europe", "Latin America", "North America"],
      datasets: [
          {
              label: "Population (millions)",
              backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850"],
              data: [2478, 5267, 734, 784, 433]
          }
      ]
  },
  options: {
      title: {
          display: true,
          text: 'Predicted world population (millions) in 2050'
      }
  }
});
//bar chart
new Chart(document.querySelector('#bar-chart-grouped'), {
  type: 'bar',
  data: {
      labels: ["1900", "1950", "1999", "2050"],
      datasets: [
          {
              label: "Africa",
              backgroundColor: "#3e95cd",
              data: [133, 221, 783, 2478]
          }, {
              label: "Europe",
              backgroundColor: "#8e5ea2",
              data: [408, 547, 675, 734]
          }
      ]
  },
  options: {
      title: {
          display: true,
          text: 'Population growth (millions)'
      }
  }
});