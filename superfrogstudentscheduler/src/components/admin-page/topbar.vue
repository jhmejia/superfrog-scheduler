<template>
    <div class="top-bar">
        
        <!-- <div class="profile-image-container">
            <img
                class="profile-image"
                src="https://ia903204.us.archive.org/4/items/discordprofilepictures/discordblue.png"
                alt="Profile Image"
            />
        </div> -->

        <p>{{ username }}</p>

        <button class="leave-button" v-on:click="goHome"> Log Out</button>
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
    methods: {
        goHome() {
            this.$router.push("/");
        },
    },
};
</script>

<style scoped>


.top-bar {
    height: 50px;
    background-color: white;
    display: flex;
    align-items: center;
    justify-content: flex-end;
    font-size: 24px;
    font-weight: bold;
    padding-right: 10px;
    border-bottom: grey 10px solid;
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

.leave-button {
    background-color: #4d2279;
    color: rgb(255, 0, 0);
    border: none;
    border-radius: 5px;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
}
</style>