<template>
    <router-view v-if="$route.path != '/'"></router-view>

    <div v-if="$route.path == '/'" id="app">
        <title>Landing Page</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css"
            rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

        <div class="landing-page">
            <div class="profile-image"></div>
            <h1 class="heading">SuperFrog Scheduler</h1>

            <p class="description">
                Add TCU Spirit to your event <br>
                Request SuperFrog, TCU Cheerleader, & Showgirls to your <br>
                wedding, grad party and more!
            </p>
            <div class="button-container">
                <!-- <RouterLink v-if="$route.path != '/'" to="/">Home</RouterLink> -->
                <button class="button" v-on:click="goToPricing"><i class="fas fa-money-bill"></i><br> Pricing</button>
                <button class="button" v-on:click="goToRange"><i class="fas fa-map-marker-alt"></i><br> Range</button>
                <button class="button" v-on:click="goToRequest"><i class="fas fa-calendar-alt"></i><br> Request</button>
                <button class="button" v-on:click="goToModifyRequest"><i class="fas fa-pencil-alt"></i><br> Modify a
                    request</button>
            </div>

            <div>
                <h2 class="login-text">Are you a SuperFrog or a Spirit Director, login below!</h2>
                <button class="login-button" v-on:click="goToLogin">Log In</button>
            </div>
                
            
        </div>

    </div>
</template>
  
<script>


export default {
    name: 'App',
    methods: {
        goToPricing() {
            this.$router.push('/pricing')
        },
        goToRange() {
            this.$router.push('/range')
        },
        goToRequest() {
            this.$router.push('/request')
        },
        goToModifyRequest() {
            this.$router.push('/modify-request')
        },
        goToLogin() {
            this.$router.push('/login')
        }
    }
}

</script>
  
<style>
.landing-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;

}



body {
    background-color: #4d2279;
}

.button-container {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    width: 80%;
}

.button {
    margin: 10px;
    padding: 20px 40px;
    font-size: 20px;
    border: none;
    border-radius: 5px;
    background-color: white;
    color: black;
    cursor: pointer;
    width: 25%;
    border-radius: 40px;
}

.login-text {
    position: absolute;
    left: 38.5%;
    bottom: 80px;
}

.login-button {
    margin: 10px;
    padding: 20px 40px;
    font-size: 20px;
    border: none;
    border-radius: 5px;
    background-color: white;
    color: black;
    cursor: pointer;
    width: 10%;
    border-radius: 40px;
    position: absolute;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
}

.button i {
    font-size: 50px;
    margin-bottom: 10px;
}

.profile-image {
    position: absolute;
    top: 20px;
    right: 20px;
    border-radius: 50%;
    width: 250px;
    height: 250px;
    background-image: url("https://gofrogs.com/common/controls/image_handler.aspx?thumb_id=12&image_path=/images/2018/9/26/SuperFrogSITE_crop_71.jpg");
    background-size: cover;
    background-position: center;
}

.heading {
    font-family: "Times New Roman", Times, serif;
    color: white;
    position: absolute;
    top: 20px;
    left: 20px;
    font-size: 50px;
    font-weight: 2000px;
}

.description {
    font-family: "Times New Roman", Times, serif;
    color: white;
    position: absolute;
    top: 80px;
    left: 20px;
    margin-top: 50px;
    font-size: 24px;
    line-height: 1.2;
}
</style>
  
    
      <!-- <RouterLink v-if="$route.path != '/'" to="/">Home</RouterLink> -->
      
