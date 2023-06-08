import { Step, StepLabel, Stepper as MuiStepper } from '@mui/material';
import { StepperProps as MuiStepperProps } from '@mui/material/Stepper/Stepper';

type StepperProps = MuiStepperProps & {
  steps: Array<{ label: string; key: string }>;
};

export const Stepper = ({ steps, ...rest }: StepperProps) => {
  return (
    <MuiStepper {...rest}>
      {steps.map(step => (
        <Step key={step.key}><StepLabel>{step.label}</StepLabel></Step>
      ))}
    </MuiStepper>
  );
};
