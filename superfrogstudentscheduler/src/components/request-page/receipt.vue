<template>
  <div style="text-align: center;">
    <h3>Thank You!</h3>
    
    Your request has been submitted.
    <br>
    An email of your request has been sent to your email.
    <br>
    Here is your request ID: {{ eventInfo.reciptId }}
    
  </div>
</template>

  
  <script>
  export default {
    name: 'ReceiptPage',
    props:{
      eventInfo: {
        type: Object
      }
    }
  }
  </script>