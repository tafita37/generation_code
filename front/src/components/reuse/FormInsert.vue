<script setup>
    import SubmitButton from './SubmitButton.vue';
    import TitleForm from './TitleForm.vue';

    const props=defineProps(['titleProps', 'handleSubmit', 'submitMessage']);
    const handleSubmit = () => {
        props.handleSubmit();
    }
</script>
<template>
    <div class="div_login">
        <div class="sous_login">
            <TitleForm :title="props.titleProps"/>
            <form @submit.prevent="handleSubmit">
                <slot></slot>
                <SubmitButton :submit-message="props.submitMessage" />
            </form>
        </div>
    </div>
</template>
<style scoped>
    .div_login {
        margin: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        background-color: #eeeffb;
    }
    
    form {
        display: flex;
        flex-direction: column;
        width: 20vw;
        padding: 30px;
        border-radius: 20px;
        height: auto;
        background-color: #ffffff;
    }
</style>