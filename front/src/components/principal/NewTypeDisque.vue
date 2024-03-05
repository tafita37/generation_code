<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertTypeDisque=reactive({
        
    });
    var responseInsertTypeDisque=reactive({});

    

    onMounted(() => {
         
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/typeDisque/insertTypeDisque";
            const response=axios.post(url, objectInsertTypeDisque);
            response.then(reponse => {
                Object.assign(responseInsertTypeDisque, reponse.data);
                console.log(responseInsertTypeDisque);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allTypeDisque");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle typeDisque" :handle-submit="handleSubmit" submit-message="Inserer la  typeDisque">
        <input v-model="objectInsertTypeDisque.nomTypeDisque" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
