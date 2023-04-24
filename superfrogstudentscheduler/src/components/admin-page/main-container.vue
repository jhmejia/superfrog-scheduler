<template>
  <div class="main-box">
    <h1>{{ section }}</h1>
    <div v-if="section === 'add-student'">
      <!-- add student form here -->
      Add dat backend to add a student. Yo 
    </div>
    <div v-if="section === 'view-students'">
      <!-- View all students here -->
      Yo, I need to add the backend to view all students, can't do it alone so I'm askin' for assistance.
    </div>
    <div v-if="section === 'add-request'">
      <!-- Add request form here -->
      Add dat backend to add a request. Yo
    </div>
    <div v-if="section === 'view-requests'">
      <!-- Display all requests here -->
      Yo, I need to add the backend to view all requests, can't do it alone so I'm askin' for assistance.
    </div>
    <div v-if="section === 'view-calendar'">
      <!-- Display calendar here -->
      Yo, I need to add the backend to view the calendar, can't do it alone so I'm askin' for assistance.
    </div>
    <div v-if="section === 'view-reports'">
      <!-- Display reports here -->
      Yo, I need to add the backend to view the reports, can't do it alone so I'm askin' for assistance.
    </div> 

  </div>
</template>

<script>
export default {
  props: {
    section: {
      type: String,
      default: "students",
    },
  },
};
</script>

<style scoped>
.main-box {
  width: 100%;
  height: 100%;
  padding: 10px;
}
</style>
