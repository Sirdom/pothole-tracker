<template>
  <div class="home">
    <map-api ref="map-api"/>
  </div>
</template>


<script>
import MapApi from '@/components/MapApi.vue'


export default {
  name: "home",
  components : {
    MapApi
  }

};
</script>
