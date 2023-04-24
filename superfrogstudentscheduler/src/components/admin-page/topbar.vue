<template>
  <div class="top-bar">
    <p>{{ username }}</p>
  </div>
</template>

<script>
export default {
  props: {
    username: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.top-bar {
  height: 50px;
  background-color: #f5f5f5;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  font-size: 24px;
  font-weight: bold;
  width: 100%;
  padding-right: 10px; /* add some padding to the right */
}

.top-bar p {
  margin: 0; /* remove default margin */
}
</style>
