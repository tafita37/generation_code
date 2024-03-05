<script setup>
    const props=defineProps(['containButton', 'eventClick', 'bg']);

    const handleSubmit = () => {
        props.eventClick();
    }
</script>
<template>
    <button @click="handleSubmit" :style="{background: props.bg}">{{ props.containButton }}</button>
</template>
<style>
    button {
        cursor: pointer;
        font-size: 14px;
        color: #ffffff;
        font-weight: 700;
        text-transform: uppercase;
        display: inline-block;
        padding: 13px 30px 12px;
        border: none;
        border-radius: 50px;
        width: auto;
        height: auto;
        margin-top: 3%;
    }
</style>