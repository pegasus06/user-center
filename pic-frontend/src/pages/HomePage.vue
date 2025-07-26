<template>
  <div id="homePage">
    <div id="main"></div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from 'vue'
import * as echarts from 'echarts';

let myChart: echarts.ECharts | null = null;

const initChart = () => {
  const chartDom = document.getElementById('main');
  if (!chartDom) return;

  myChart = echarts.init(chartDom);
  const option = {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [{
      data: [150, 230, 224, 218, 135, 147, 260],
      type: 'line'
    }]
  };
  myChart.setOption(option);
};

const handleResize = () => {
  myChart?.resize();
};

onMounted(() => {
  initChart();
  window.addEventListener('resize', handleResize);
});

onUnmounted(() => {
  myChart?.dispose();
  window.removeEventListener('resize', handleResize);
});
</script>

<style scoped>
#homePage {
  width: 100%;
  height: 100%;
  padding: 20px;
}

#main {
  width: 100%;
  height: 100%;
  min-height: 400px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}
</style>