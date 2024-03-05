<script setup>
    const props=defineProps(['titleListe']);
</script>
<template>
    <h3>
        {{ props.titleListe }}
    </h3>
</template>
<style scoped>
    h3 {
        color: #111111;
        font-weight: 700;
        text-transform: uppercase;
        position: relative;
        text-align: center;
    }
</style>