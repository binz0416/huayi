<template>
<div>
  <div class="levelrow" style="background-color:#000000;">
    <DreamTree/>
  </div>
</div>
</template>

<script>
import DreamTree from './dreamtree'

export default {
  name: 'Main',
  components: {
    DreamTree
  }
}
</script>

<style>
.levelrow {
  margin-top: -15px;
  margin-right: -15px;
  margin-left: -15px;
  margin-bottom: -15px;
}
</style>
