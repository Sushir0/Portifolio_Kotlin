class ChartFactory {
  constructor(elementId, graficoTotalSemanal, graficoTotalMensal, graficoTotalAnual) {
      this.elementId = elementId;
      this.weeklyData = graficoTotalSemanal;
      this.monthlyData = graficoTotalMensal;
      this.annualData = graficoTotalAnual;
      this.chart = null;
  }

  formatDate(date) {
      return new Intl.DateTimeFormat('pt-BR').format(date);
  }

  #formatDateForAPI(date) {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
  }

  #updateChart(labels, data) {
      const ctx = document.getElementById(this.elementId).getContext('2d');
      if (this.chart) this.chart.destroy();
      
      this.chart = new Chart(ctx, {
          type: 'bar',
          data: {
              labels: labels,
              datasets: [{
                  label: 'Visitas',
                  data: data,
                  backgroundColor: 'rgba(54, 162, 235, 0.5)',
                  borderColor: 'rgba(54, 162, 235, 1)',
                  borderWidth: 1
              }]
          },
          options: {
              responsive: true,
              maintainAspectRatio: false,
              scales: { y: { beginAtZero: true } }
          }
      });
  }

  update7Days() {
      const labels = [];
      const dataPoints = [];
      const today = new Date();
      today.setHours(0, 0, 0, 0);

      for (let i = 6; i >= 0; i--) {
          const currentDate = new Date(today);
          currentDate.setDate(currentDate.getDate() - i);
          const dateStr = this.#formatDateForAPI(currentDate);
          const visitGraph = this.weeklyData.find(item => item.start_date === dateStr);
          labels.push(this.formatDate(currentDate));
          dataPoints.push(visitGraph ? visitGraph.view_count : 0);
      }

      this.#updateChart(labels, dataPoints);
  }

  update30Days() {
      const labels = [];
      const dataPoints = [];
      const today = new Date();
      today.setHours(0, 0, 0, 0);

      for (let i = 9; i >= 0; i--) {
          const startDate = new Date(today);
          startDate.setDate(startDate.getDate() - (i * 3 + 2));
          const startDateStr = this.#formatDateForAPI(startDate);
          const visitGraph = this.monthlyData.find(item => item.start_date === startDateStr);
          const endDate = new Date(startDate);
          endDate.setDate(startDate.getDate() + 2);
          labels.push(`${this.formatDate(startDate)} - ${this.formatDate(endDate)}`);
          dataPoints.push(visitGraph ? visitGraph.view_count : 0);
      }

      this.#updateChart(labels, dataPoints);
  }

  update1Year() {
      const labels = [];
      const dataPoints = [];
      const months = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
      const currentDate = new Date();
      currentDate.setDate(1); // Garante que estamos no primeiro dia do mês

      for (let i = 11; i >= 0; i--) {
          const targetDate = new Date(currentDate);
          targetDate.setMonth(targetDate.getMonth() - i);
          targetDate.setDate(1);
          const startDateStr = this.#formatDateForAPI(targetDate);
          const visitGraph = this.annualData.find(item => item.start_date === startDateStr);
          const monthName = months[targetDate.getMonth()];
          labels.push(`${monthName}/${targetDate.getFullYear()}`);
          dataPoints.push(visitGraph ? visitGraph.view_count : 0);
      }

      this.#updateChart(labels, dataPoints);
  }
}

// Exemplo de uso:
// const myChart = new ChartFactory('myChart', graficoSemanal, graficoMensal, graficoAnual);
// myChart.update7Days();