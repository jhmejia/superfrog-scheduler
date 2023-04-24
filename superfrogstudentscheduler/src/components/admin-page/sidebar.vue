<template>
    <div class="sidebar">
      <ul>
        <li @click="selectSection('students')">View All Students</li>
        <li @click="selectSection('requests')">View All Requests</li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      selectSection(section) {
        this.$emit('section-selected', section);
      },
    },
  };
  </script>
  
  <style scoped>
  .sidebar {
    width: 20%;
    height: 100%;
    background-color: #f5f5f5;
    padding: 10px;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin: 10px 0;
    cursor: pointer;
  }
  </style>
  