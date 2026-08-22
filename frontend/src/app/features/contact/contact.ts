import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ContactService } from '../../core/services/contact.service';

@Component({
  selector: 'app-contact',
  imports: [ReactiveFormsModule],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class Contact {
private fb = inject(FormBuilder);
  private contactService = inject(ContactService);

  isSubmitting = false;
  successMessage = '';
  errorMessage = '';

  contactForm = this.fb.nonNullable.group({

    name: ['', [
      Validators.required,
      Validators.maxLength(100)
    ]],

    email: ['', [
      Validators.required,
      Validators.email,
      Validators.maxLength(255)
    ]],

    subject: ['', [
      Validators.maxLength(200)
    ]],

    message: ['', [
      Validators.required,
      Validators.maxLength(2000)
    ]]
  });

  submit(): void {

    this.successMessage = '';
    this.errorMessage = '';

    if (this.contactForm.invalid) {
      this.contactForm.markAllAsTouched();
      return;
    }

    this.isSubmitting = true;

    this.contactService
      .sendMessage(this.contactForm.getRawValue())
      .subscribe({

        next: () => {

          this.successMessage =
            'Your message has been sent successfully!';

          this.contactForm.reset();

          this.isSubmitting = false;
        },

        error: () => {

          this.errorMessage =
            'Unable to send your message. Please try again later.';

          this.isSubmitting = false;
        }
      });
  }

  isFieldInvalid(fieldName: string): boolean {

    const field = this.contactForm.get(fieldName);

    return !!field &&
           field.invalid &&
           (field.dirty || field.touched);
  }

}


