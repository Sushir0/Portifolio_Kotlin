const tagsContainer = document.getElementById('tagsContainer');
        const tagInput = document.getElementById('tagInput');

        // Função para criar novo item de tag
        function createTagItem(text) {
            const tagItem = document.createElement('div');
            tagItem.className = 'tag-item';

            const textSpan = document.createElement('span');
            textSpan.className = 'tag-text';
            textSpan.textContent = text;

            const editButton = document.createElement('button');
            editButton.className = 'tag-button edit';
            editButton.innerHTML = '✏️';
            editButton.onclick = () => editTag(textSpan);

            const deleteButton = document.createElement('button');
            deleteButton.className = 'tag-button delete';
            deleteButton.innerHTML = '🗑️';
            deleteButton.onclick = () => tagItem.remove();

            tagItem.appendChild(textSpan);
            tagItem.appendChild(editButton);
            tagItem.appendChild(deleteButton);

            return tagItem;
        }

        // Função para editar tag
        function editTag(textSpan) {
            const oldText = textSpan.textContent;
            const input = document.createElement('input');
            input.type = 'text';
            input.className = 'edit-input';
            input.value = oldText;
            
            const tagItem = textSpan.parentElement;
            tagItem.replaceChild(input, textSpan);
            input.focus();

            input.addEventListener('keypress', (e) => {
                if (e.key === 'Enter') {
                    const newText = input.value.trim();
                    if (newText) {
                        textSpan.textContent = newText;
                        tagItem.replaceChild(textSpan, input);
                    }
                }
            });

            input.addEventListener('blur', () => {
                textSpan.textContent = input.value.trim() || oldText;
                tagItem.replaceChild(textSpan, input);
            });
        }

        // Adicionar tag ao pressionar Enter
        tagInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                const text = tagInput.value.trim();
                if (text) {
                    tagsContainer.appendChild(createTagItem(text));
                    tagInput.value = '';
                }
            }
        });