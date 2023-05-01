<!-- <template>
    <div class="policy-page">
      <p class="title">TCU Spirit Team Terms and Conditions</p>
      <ul>
        <li>
          <p>Submission of a request does not guarantee the event will be accepted</p>  
        </li>
        <li>
            <p>TCU athletics takes priorty over all other apperances</p>
        </li>
        <li>
          <p>TCU Spirit has until 5 days prior to the date of the event to accept or decline a request</p>
        </li>
        <li>
          <p>Once approved via email, payment must be submitted a minimum of 24 hours prior to the event or it will be canceled</p>
        </li>
        <li>
          <p>Even after an event is confirmed, it is still subjected to immediate cancellation due to a TCU Athletic Department function
            , weather or an emergency. Immediate notice will be given in the event of cancellation.
          </p>
        </li>
        <li>
          <p>The SuperFrog suit is NOT rented out to a third party</p>
        </li>
      </ul>
      
    </div>
  </template>
  
  <script>
  export default {
    name: 'PolicyPage',
    
  }
  </script>

<style scoped>
.policy-page {
  text-align: left;
}
p {
  margin: 0;
}
.title {
  text-align: center;
  font-size: 1.5em;
}
li {
  list-style-type: disc;
  margin-bottom: 0.5em;
  display: block;
}
.disc li {
  display: block;
}
ul {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

</style> -->

<template>
  <div class="policy-page">
    <p class="title">TCU Spirit Team Terms and Conditions</p>
    <ul>
      <li>Submission of a request does not guarantee the event will be accepted</li>  
      <li>TCU athletics takes priority over all other appearances</li>
      <li>TCU Spirit has until 5 days prior to the date of the event to accept or decline a request</li>
      <li>Once approved via email, payment must be submitted a minimum of 24 hours prior to the event or it will be canceled</li>
      <li>Even after an event is confirmed, it is still subject to immediate cancellation due to a TCU Athletic Department function, weather, or an emergency. Immediate notice will be given in the event of cancellation.</li>
      <li>The SuperFrog suit is NOT rented out to a third party</li>
    </ul>
    <div class="checkbox-container">
      <input type="checkbox" id="agree" v-model="isChecked">
      <label for="agree">I agree to the terms & conditions of a TCU Spirit Appearance</label>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'PolicyPage',
    data() {
    return {
      isChecked: false
    }
  }
  }
  </script>

<style scoped>
.policy-page {
  text-align: left;
}
p {
  margin: 0;
}
.title {
  text-align: center;
  font-size: 1.5em;
}
ul {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  list-style-type: disc;
  margin-left: 2em;
}
.checkbox-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 20px;
}
</style>
