<template>
    <div class="container">
      <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
      <sidebar @section-selected="updateSection"></sidebar>
  
      <div class="right">
        <top-bar username="Admin"></top-bar>
        <main-box :section="selectedSection"></main-box>
      </div>
    </div>
  </template>
  
  <script>
  import Sidebar from "./sidebar.vue";
  import MainBox from "./main-container.vue";
  import TopBar from "./topbar.vue";
  
  export default {
    name: "AdminPage",
    components: {
      Sidebar,
      MainBox,
      TopBar,
    },
    data() {
      return {
        selectedSection: "students",
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
    height: auto;
    width: auto;
    background-color: white;
    color: black;
  }

  
  .right {
    display: flex;
    flex-direction: column;
    width: 80vw;
  }
  </style>
