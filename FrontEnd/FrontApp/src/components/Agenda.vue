<template>
    <div class="inicio">

        <Button label="Inserir Contato" class="p-button-raised p-button-success" icon="pi pi-plus"
            style=" width: 200px; height: 45px; font-size: 19px; font-weight: 700;" id="inseir" @click="openInsert" />





    </div>
    <div class="inicio" style="margin-top: 5px;">



        <Button label="Criar Conta" class="p-button-raised p-button-primary" icon="pi pi-user-plus"
            style=" width: 200px; height: 45px; font-size: 25px; font-weight: 700;" id="inseir" @click="abrirLogin" />



    </div>

    <Dialog v-model:visible="displayCreate" :style="{ width: '900px' }" :modal="true" class="p-fluid">
        <div class="card">
            <h2 class="text-center">Inserir Cadastro</h2>
            <form class="p-fluid">
                <div class="field">
                    <div class="p-inputgroup">
                        <span class="p-inputgroup-addon">
                            <i class="pi pi-user"></i>
                        </span>
                        <InputText placeholder="Nome" v-model="contato.name" required />
                    </div>
                </div>
                <div class="field">
                    <div class="p-inputgroup">
                        <span class="p-inputgroup-addon">
                            <i class="pi pi-envelope"></i>
                        </span>
                        <InputText placeholder="Email" v-model="contato.email" />
                    </div>
                </div>
                <div class="field">
                    <div class="p-inputgroup">
                        <span class="p-inputgroup-addon">
                            <i class="pi pi-phone"></i>
                        </span>
                        <InputText placeholder="Telefone" v-model="contato.phone" required />
                    </div>
                </div>
                <Button type="submit" label="Enviar" class="mt-2" @click="addAgenda"></Button>

            </form>
        </div>
    </Dialog>

    <Dialog v-model:visible="displayLogin" :style="{ width: '900px' }" :modal="true" class="p-fluid">
        <div class="card">
            <h2 class="text-center" style="margin-bottom: 2rem; font-weight: bold; color: blue;">Criar Login</h2>
            <form class="p-fluid">
                <div class="field">
                    <div class="p-inputgroup">
                        <span class="p-inputgroup-addon">
                            <i class="pi pi-user"></i>
                        </span>
                        <InputText placeholder="Usuario" v-model="login.username" required />
                    </div>
                </div>
                <div class="field">
                    <div class="p-inputgroup">
                        <span class="p-inputgroup-addon">
                            <i class="pi pi-lock"></i>
                        </span>
                        <InputText type="password" placeholder="Senha" id="senha" v-model="login.password" />
                        <i class="pi pi-eye" style="font-size: 30px; margin-left: 10px;" @mousemove="showSenha"
                            @mouseleave="hideSenha"></i>


                    </div>

                </div>

                <Button type="submit" label="Enviar" class="mt-2" @click="addLogin"></Button>

            </form>
        </div>
    </Dialog>


    <div class="table">

        <DataTable :value="usuarios" id="tabela" style="">
            <column id="colunas" field="name" header="Nome"></column>
            <column id="colunas" field="email" header="E-mail"></column>
            <column id="colunas" field="phone" header="Telefone"></column>
            <Column id="colunabotao" header="" style="padding: 20px">
                <template #body="slotProps">
                    <Button label="Editar" id="botoes" icon="pi pi-user-edit" class="p-button-rounded p-button-warning"
                        style="border-radius: 10px;margin: 5 auto;max-width: 400px;
        			padding: 20px;" @click="openEdit(slotProps.data)" />
                    <Button label="Deletar" id="botoes" icon="pi pi-trash" class="p-button-rounded p-button-danger" style="border-radius: 10px;
        			margin: 5 auto;
        			max-width: 400px;
        			padding: 20px;" @click="$event => deleteDialogbox(slotProps.data)" />
                </template>
            </Column>
        </DataTable>
    </div>

    <Dialog v-model:visible="displayBasic" :style="{ width: '450px' }" header="Editar Cadastro" :modal="true"
        class="p-fluid">
        <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-user"></i>
                </span>
                <InputText placeholder="Nome" v-model="alguem.name" />
            </div>
        </div>
        <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-envelope"></i>
                </span>
                <InputText placeholder="Email" v-model="alguem.email" />
            </div>
        </div>
        <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-phone"></i>
                </span>
                <InputText placeholder="Telefone" v-model="alguem.phone" />
            </div>
        </div>
        <template #footer>
            <Button label="Sair" icon="pi pi-times" @click="closeEdit" autofocus class="p-button-text" />
            <Button label="Salvar" icon="pi pi-check" @click="updateAgenda(alguem.id), closeEdit" />
        </template>
    </Dialog>

    <Dialog v-model:visible="deleteDialog" :style="{ width: '650px' }" header="Deletar" :modal="true">

        <div class="textodelete">
            Tem certeza que deseja deletar o usuario?
        </div>
        <Div class="botaoDeletar">

            <Button label="Sair" icon="pi pi-times" style="background-color: #f2f2f2; width: 120px;height:45px ;"
                @click="closeDelete" autofocus class="p-button-text" />
            <Button label="Deletar" icon="pi pi-trash"
                style="background-color: red;width: 120px;height:45px ;margin-left: 10px;"
                @click="deleteAgenda(pessoa.id)" />
        </Div>
    </Dialog>

    <Dialog v-model:visible="LoginOk" style="width: 300px; height: 300px;" header="Sucesso" :modal="true">

        <h2>
            Usuario e Senha Criados com Sucesso!
        </h2>


    </Dialog>
</template>

<script>
import { transform } from '@vue/compiler-core';
import axios from '../configs/request';
export default {
    name: "Agenda",
    data() {
        return {
            usuarios: [],
            contato: {
                name: '',
                phone: '',
                email: ''
            },
            login: {
                username: '',
                password: ''

            }
            ,
            displayBasic: false,
            displayCreate: false,
            deleteDialog: false,
            displayLogin: false,
            senhaexposta: false,
            LoginOk: false





        }
    },
    async mounted() {
        this.loadData()

    },
    methods: {
        async loadData() {
            let result = await axios.get("/api/agenda",{
                auth:{ password: this.password,
                     username: this.username}

            });
            this.usuarios = result.data
            console.table(result.phone);
        },
        async addAgenda() {

            const result = await axios.post("/api/agenda", this.contato)
                .then(result => {
                    this.$toast.add({ severity: 'success', summary: 'Success Message', detail: 'Message Content', life: 3000 });
                    this.loadData()
                })
                .catch(error => {
                    alert("Dados incorretos ou já cadastrados, por favor, verique os campos e tente novamente.");
                });

            if (result.status == 200) {

                this.loadData();
                this.LoginOk = true;
            }

        },
        async addLogin() {

            const result = await axios.post("/api/login", this.login)
                .then(result => {
                    alert("Login e Senha Registrados Com Sucesso!")
                    this.LoginOk = true;
                })
                .catch(error => {
                    alert("Dados incorretos ou já cadastrados, por favor, verique os campos e tente novamente.");
                });

            if (result.status == 201) {
                alert("Login e Senha Registrados Com Sucesso!")
                this.LoginOk = true;
            }

        }
        ,
        openInsert(agenda) {
            this.displayCreate = true;
        },

        deleteDialogbox(agenda) {
            this.pessoa = { ...agenda }

            this.deleteDialog = true;


        },
        closeDelete() {
            this.deleteDialog = false;
            this.loadData();
        }
        ,
        showSenha() {
            document.getElementById('senha').type = 'text';

        }
        ,
        hideSenha() {
            document.getElementById('senha').type = 'password';
        }
        ,


        openEdit(agenda) {

            this.displayBasic = true;
            this.alguem = { ...agenda };
        },
        closeEdit() {
            this.displayBasic = false;
            this.loadData()
        },
        abrirLogin() {
            this.displayLogin = true;

        },
        fecharLogin() {
            this.displayLogin = false;
            this.loadData();

        },

        async deleteAgenda(id) {
            let result = await axios.delete('/api/agenda/' + id);
            console.warn(result);
            if (result.status == 200)
                this.deleteDialog = false;
            alert("Deletedo com sucesso")
            this.loadData()
        },
        async updateAgenda(id) {

            const result = await axios.put("/api/agenda/" + id, {
                name: this.alguem.name,
                email: this.alguem.email,
                phone: this.alguem.phone,
            });

            if (result.status == 400) {
                this.displayBasic = true;
                alert("Dados incorretos ou já cadastrados, por favor, verique os campos e tente novamente.");


            }


            if (result.status == 200)
                this.displayBasic = false;
            this.loadData();
        }


    },
}







</script>

<style>
.table {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 2px;
    width: 90%;
    padding: 5px;
    box-sizing: content-box;
    padding: 30px 40px 30px 40px;

}

#tabela {
    display: block;
    justify-content: center;
    align-items: center;
    box-sizing: content-box;
    padding: 2px;
    box-sizing: content-box;
    width: 90%;
    margin-left: 4rem;
    box-shadow: -10px 20px 20px 4px rgba(29, 39, 41, 0.801);


}

#botoes {
    margin: 4px;
}

#colunas {
    padding: 2px;
    width: 10px;
    box-sizing: content-box;
}

.inicio {
    display: flex;
    align-items: e;
    justify-content: end;
    width: 80%;
    margin-top: 9rem;
    margin-left: 130px;

}

#inserir {
    display: flex;
    width: 20rem;
    margin-left: 90px;
    align-items: flex-start;


}

.botaoDeletar {
    display: flex;
    align-items: end;
    justify-content: end;
    padding: 10px;
    margin: 2px;
}

.textodelete {
    display: flex;
    justify-content: flex-start;
    padding: 2px;
    margin-bottom: 20px;
    border-bottom: solid black 2px;

    font-weight: bolder;
}

#colunabotao {
    display: flex;
    justify-content: end;
    border: solid black 5px;
    box-sizing: content-box;
    align-items: start;
    background-color: blueviolet;


}
</style>