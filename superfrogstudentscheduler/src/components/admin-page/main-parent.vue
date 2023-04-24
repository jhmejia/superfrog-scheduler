<template>
    <div class="container">
      <top-bar username="Admin"></top-bar>
      <sidebar @section-selected="updateSection"></sidebar>
      <main-box :section="selectedSection"></main-box>
    </div>
  </template>
  
  <script>
  import Sidebar from './sidebar.vue';
  import MainBox from './main-container.vue';
  import TopBar from './topbar.vue';
  
  export default {
    name: 'AdminPage',
    components: {
      Sidebar,
      MainBox,
      TopBar,
    },
    data() {
      return {
        selectedSection: 'students',
        
      };
    },
    methods: {
      updateSection(section) {
        this.selectedSection = section;
      },
    },
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    flex-direction: row;
    height: 100vh;
  }
  </style>
  