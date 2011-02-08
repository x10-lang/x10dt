#ifndef __X10_COMPILER_FINALIZATION_H
#define __X10_COMPILER_FINALIZATION_H

#include <x10rt.h>


#define X10_LANG_THROWABLE_H_NODEPS
#include <x10/lang/Throwable.h>
#undef X10_LANG_THROWABLE_H_NODEPS
#define X10_LANG_BOOLEAN_STRUCT_H_NODEPS
#include <x10/lang/Boolean.struct_h>
#undef X10_LANG_BOOLEAN_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Any;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace compiler { 
class CompilerFlags;
} } 
namespace x10 { namespace compiler { 

class Finalization : public x10::lang::Throwable   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Any> FMGL(value);
    
    x10aux::ref<x10::lang::String> FMGL(label);
    
    x10_boolean FMGL(isReturn);
    
    x10_boolean FMGL(isBreak);
    
    x10_boolean FMGL(isContinue);
    
    static void throwReturn();
    static void throwReturn(x10aux::ref<x10::lang::Any> v);
    static void throwBreak();
    static void throwBreak(x10aux::ref<x10::lang::String> l);
    static void throwContinue();
    static void throwContinue(x10aux::ref<x10::lang::String> l);
    static void plausibleThrow();
    virtual x10aux::ref<x10::compiler::Finalization> x10__compiler__Finalization____x10__compiler__Finalization__this(
      );
    void _constructor();
    
    static x10aux::ref<x10::compiler::Finalization> _make();
    
    void __fieldInitializers1796();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_COMPILER_FINALIZATION_H

namespace x10 { namespace compiler { 
class Finalization;
} } 

#ifndef X10_COMPILER_FINALIZATION_H_NODEPS
#define X10_COMPILER_FINALIZATION_H_NODEPS
#include <x10/lang/Throwable.h>
#include <x10/lang/Any.h>
#include <x10/lang/String.h>
#include <x10/lang/Boolean.h>
#include <x10/compiler/CompilerFlags.h>
#ifndef X10_COMPILER_FINALIZATION_H_GENERICS
#define X10_COMPILER_FINALIZATION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::compiler::Finalization::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::compiler::Finalization> this_ = new (memset(x10aux::alloc<x10::compiler::Finalization>(), 0, sizeof(x10::compiler::Finalization))) x10::compiler::Finalization();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_COMPILER_FINALIZATION_H_GENERICS
#endif // __X10_COMPILER_FINALIZATION_H_NODEPS
