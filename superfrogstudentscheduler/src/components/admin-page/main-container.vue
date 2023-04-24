<template>
    <div class="main-box">
      <h1>{{ section }}</h1>
      <div v-if="section === 'students'">
        <!-- Display all students here -->
        I AM THE STUDENTS SECTION
      </div>
      <div v-else-if="section === 'requests'">
        <!-- Display all requests here -->
        SHOW THE REQUESTS
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      section: {
        type: String,
        default: 'students',
      },
    },
  };
  </script>
  
  <style scoped>
  .main-box {
    width: 80%;
    height: 100%;
    padding: 10px;
  }
  </style>
  