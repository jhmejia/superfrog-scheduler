<template class="main">
  <div class="date">
    Select date
  <VueDatePicker
    :enable-time-picker="false"
    v-model="eventInfo.eventDate"
    :model-value="eventInfo.eventDate"
    model-type="yyyy-MM-dd"
    @closed="updateDate"
    :format="formatDate"
    
  />
</div>
<div class="time">
  <span>Select starting time</span>
  <VueDatePicker
    :model-value="eventInfo.startTime"
    time-picker
    model-type="HH:mm:ss"
    @closed="updateStart"
    :format="formatS"
    :is-24="false"
    
    />
    <span>Select ending time</span>
    <VueDatePicker
    
    :model-value="eventInfo.endTime"
    time-picker
    model-type="HH:mm:ss"
    :format="formatE"
    @closed="updateEnd"
    :is-24="false"
    
    />
  </div>
</template>
  
  <script>

 
  import VueDatePicker from '@vuepic/vue-datepicker';
  import '@vuepic/vue-datepicker/dist/main.css'
  import { format } from 'date-fns';
  import { ref } from 'vue';

  export default {
    name: 'DateTimePage',
    data() {
      return {
        startTime: ref(new Date()),
        endTime: ref(new Date()),
        scheduleDate: ref(new Date()),
        
      }
    },
    computed:{
      
    },
    props: {
      eventInfo: {
        type: Object
      }
      
    },
    components: {
      VueDatePicker

    },
    methods: {
      updateParent() {
      this.$emit('update', {
        eventInfo: this.eventInfo,
        })},
        updateStart(){
          this.eventInfo.startTime = this.startTime;
          this.updateParent();
        },
        updateEnd(){
          this.eventInfo.endTime = this.endTime;
          this.updateParent();
        },
        updateDate(){
          this.eventInfo.scheduleDate = this.scheduleDate;
          this.updateParent();
        },
        formatS(date) {
          this.eventInfo.startTime = format(date, 'hh:mm:ss');
          return format(date, 'hh:mm:ss a');
        },
        formatE(date) {
          this.eventInfo.endTime = format(date, 'hh:mm:ss');
          return format(date, 'hh:mm:ss a ');
          },
        formatDate(date) {
          this.eventInfo.scheduleDate = format(date, 'yyyy-MM-dd');
          return format(date, 'yyyy-MM-dd');
        },
        }
  }

    
  </script>

  <style scoped>
  .date{
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
    margin: 10px;
  }
  .time{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: space-between;

  }
  </style>
