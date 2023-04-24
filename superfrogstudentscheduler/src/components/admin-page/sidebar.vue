<template>
  <div class="sidebar">
    <div class="section">
      <div class="section-header" @click="toggleSection('students')">
        <i
          class="icon"
          :class="[
            isExpanded('students')
              ? 'fa fa-chevron-down'
              : 'fa fa-chevron-right',
          ]"
        ></i>
        SuperFrog Students
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
        <i
          class="icon"
          :class="[
            isExpanded('requests')
              ? 'fa fa-chevron-down'
              : 'fa fa-chevron-right',
          ]"
        ></i>
        Requests
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
          <i class="icon fa fa-file-text"></i>
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
  width: 200px;
  background-color: #f5f5f5;
  padding: 20px;
}

.section {
  margin-bottom: 20px;
}

.section-header {
  font-size: 18px;
  font-weight: bold;
  cursor: pointer;
}

.icon {
  margin-right: 10px;
}

.sub-section {
  margin-left: 20px;
  cursor: pointer;
}
</style>
