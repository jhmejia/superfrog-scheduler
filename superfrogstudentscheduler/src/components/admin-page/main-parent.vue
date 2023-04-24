<template>
  <div class="container">
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
  height: 100vh;
  width: 100vw;
}

.right {
  display: flex;
  flex-direction: column;
  width: 70%;
}
</style>
