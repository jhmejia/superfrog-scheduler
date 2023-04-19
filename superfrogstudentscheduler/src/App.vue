
<template>
  
    <RouterLink v-if="$route.path != '/'" to="/">Home</RouterLink>
    <RouterLink v-if="$route.path == '/'" to="/pricing">Pricing</RouterLink>
    <RouterLink v-if="$route.path == '/'" to="range">Range</RouterLink>
    <RouterLink v-if="$route.path == '/'" to="/request">Request</RouterLink>
    <RouterLink v-if="$route.path == '/'" to="/modify-request">Modify a request</RouterLink>
  
    <router-view v-if="$route.path !='/'"></router-view>

</template>


<script>

</script>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
