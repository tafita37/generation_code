<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertTypeRam=reactive({
        
    });
    var responseInsertTypeRam=reactive({});

    

    onMounted(() => {
         
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/typeRam/insertTypeRam";
            const response=axios.post(url, objectInsertTypeRam);
            response.then(reponse => {
                Object.assign(responseInsertTypeRam, reponse.data);
                console.log(responseInsertTypeRam);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allTypeRam");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle typeRam" :handle-submit="handleSubmit" submit-message="Inserer la  typeRam">
        <input v-model="objectInsertTypeRam.nomTypeRam" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
