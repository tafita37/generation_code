<script setup>
    const props=defineProps(['submitMessage']);
</script>
<template>
    <button type="submit" class="site-btn">{{ props.submitMessage }}</button>
</template>
<style scoped>
    button[type="submit"] {
        background-color: #4c57d6;
        cursor: pointer;
        font-size: 14px;
        color: #ffffff;
        font-weight: 700;
        text-transform: uppercase;
        display: inline-block;
        padding: 13px 30px 12px;
        border: none;
        border-radius: 50px;
    }
</style>