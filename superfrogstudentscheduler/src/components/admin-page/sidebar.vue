<template>
    <div class="sidebar">
      <div class="section">
  
          <img class="superfrog-img" src="https://content.sportslogos.net/logos/34/868/full/tcu_horned_frogs_logo_secondary_19976347.png" alt="SuperFrog" >
  
  
        <div class="section-header" @click="toggleSection('students')">
          <i class="icon fa fa-address-book"></i>
          SuperFrog Students 
          <i
            class="icon"
            :class="[
              isExpanded('students')
                ? 'fa fa-chevron-down'
                : 'fa fa-chevron-right',
            ]"
          ></i>
        </div>
        <div class="section-content" v-show="isExpanded('students')">
          <div class="sub-section" @click="selectSection('add-student')">
            Add a new student
          </div>
          <div class="sub-section" @click="selectSection('view-students')">
            View all students
          </div>
        </div>
  
        <div class="section-header" @click="toggleSection('requests')">
          <i class="icon fa fa-list-alt"></i>
          Requests
          <i
            class="icon"
            :class="[
              isExpanded('requests')
                ? 'fa fa-chevron-down'
                : 'fa fa-chevron-right',
            ]"
          ></i>
        </div>
        <div class="section-content" v-show="isExpanded('requests')">
          <div class="sub-section" @click="selectSection('add-request')">
            Add a new request
          </div>
          <div class="sub-section" @click="selectSection('view-requests')">
            View all requests
          </div>
        </div>
  
        <div class="section">
          <div class="section-header" @click="selectSection('view-calendar')">
            <i class="icon fa fa-calendar"></i>
            Calendar
          </div>
        </div>
        <div class="section">
          <div class="section-header" @click="selectSection('view-reports')">
            <i class="icon fa fa-file"></i>

            Reports
          </div>
        </div>
      </div>
    </div>
  </template>
  
  
  
  
  <script>
  export default {
    data() {
      return {
        expandedSections: [],
        selectedSection: "",
      };
    },
    methods: {
      toggleSection(section) {
        if (this.expandedSections.includes(section)) {
          this.expandedSections = this.expandedSections.filter(
            (s) => s !== section
          );
        } else {
          this.expandedSections.push(section);
        }
      },
      isExpanded(section) {
        return this.expandedSections.includes(section);
      },
      selectSection(section) {
        this.selectedSection = section;
        this.$emit("section-selected", section);
      },
    },
  };
  </script>
  
  <style scoped>
  .sidebar {
    width: 300px;
    background-color: #4d2279;
    padding: 20px;
  }
  
  .section {
    margin-bottom: 20px;
    color: #ffffff;
  }
  
  .section-header {
    font-size: 19px;
    margin-top: 50px;
    font-weight: bold;
    cursor: pointer;
    text-align: left;
    color: #ffffff;
  }
  
  .icon {
    margin-right: 10px;
  }
  
  .sub-section {
    margin-left: 20px;
    cursor: pointer;
  }
  .superfrog-img{
    width: 120px;
    margin-left: 65px;
  }
  </style>
