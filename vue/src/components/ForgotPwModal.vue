<template>
    <div class="forgotPw">
      <div class="modal-backdrop">
        <div class="modal">
          <header class="modal-header">
            <slot name="header">
                Forgot password?
            </slot>
            <button type="button" class="btn-close" @click="close">
                x
            </button>
          </header>

        <section class="modal-body">
            <slot name="body">
                To reset your password, please email our Customer Care Center at helpdesk@wefixpotholes.com.
                A support specialist will help you with next steps.
            </slot>
        </section>

        <footer class="modal-footer">
            <slot name="footer">
            </slot>
            <button type="button" class="btn-green" @click="close">
                close
            </button>
        </footer>
        </div>
     </div>
    </div>
</template>

<script>
export default {
    name: 'modal',
    methods: {
        close() {
            this.$emit('close');
        }
    }
}
</script>

<style>

.forgotPw {
  display: flex;
  justify-content: center;
  margin-bottom: 5%;
}

  .modal-backdrop {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .modal {
    background: #FFFFFF;
    box-shadow: 2px 2px 20px 1px;
    overflow-x: auto;
    display: flex;
    flex-direction: column;
  }

  .modal-header,
  .modal-footer {
    padding: 15px;
    display: flex;
  }

  .modal-header {
    font-family: 'Ubuntu', sans-serif;
    position: relative;
    border-bottom: 1px solid #eeeeee;
    color: #8C55AA;
    justify-content: space-between;
  }

  .modal-footer {
    border-top: 1px solid #eeeeee;
    flex-direction: column;
    justify-content: flex-end;
  }

  .modal-body {
    font-family: 'Ubuntu', sans-serif;
    position: relative;
    padding: 20px 10px;
  }

  .btn-close {
    position: absolute;
    top: 0;
    right: 0;
    border: none;
    font-size: 20px;
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    color: #8C55AA;
    background: transparent;
  }

  .btn-green {
    color: white;
    background: #8C55AA;
    border: 1px solid #8C55AA;
    border-radius: 2px;
  }

</style>