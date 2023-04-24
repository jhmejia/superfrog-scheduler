<template>
    <div class="top-bar">
        <div class="profile-image-container">
            <img
                class="profile-image"
                src="https://ia903204.us.archive.org/4/items/discordprofilepictures/discordblue.png"
                alt="Profile Image"
            />
        </div>

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

profile-image-container {
    height: 50px;
    padding-top: 5px;
    padding-bottom: 5px;
    display: flex;
    align-items: center;
}

.profile-image {
    height: 50px;
    width: 50px;
    border-radius: 50%;
}
</style>
