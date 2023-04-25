<template>
    <div class="map-container">
        <div id="map">
            <iframe
                width="600"
                height="450"
                style="border: 0"
                loading="lazy"
                allowfullscreen
                src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJFV0auj5yToYRgzlMof_OSfo&key=AIzaSyADl1BSYCI5OWl9n7Ppw4cJ5nMo3yEFA2c"
            ></iframe>
        </div>
    </div>
</template>

<script>
export default {
    name: "GoogleMap",
};
</script>

<style scoped>
.map-container {
    height: 100%;
}

#map {
    height: 100%;
}
</style>
